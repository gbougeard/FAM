/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamUserFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cdi.LoggedIn;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@SessionScoped
@Named
public class Login implements Serializable {

    private static final long serialVersionUID = 7965455427888195913L;
    //
    @Inject
    private Credentials credentials;
    @Inject
    private FamUserFacade userManager;

    private FamUser currentUser;
    //
    private String userSuppliedId; //Users OpenID URL
    private String validatedId;
    private String openIdEmail;
    private String openIdFullName;
    private String openIdFirstName;
    private String openIdLastName;
    private String openIdCountry;
    //
    private ConsumerManager manager;
    private DiscoveryInformation discovered;
    private final static String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";
    private final static String YAHOO_ENDPOINT = "http://me.yahoo.com";
    //
    private final static String AUTH_SIGNUP_SUCCEED = "/auth/afterSignup.xhtml";
    private final static String AUTH_SIGNUP_FAILED = "/auth/loginError.xhtml";
    private final static String AUTH_SUCCEED = "/openid.xhtml";
    private final static String AUTH_FAILED = "/auth/loginError.xhtml";
    //
    private Boolean signup = Boolean.TRUE;
    private String onLoad;


    public String login() {
        StringBuilder sb = new StringBuilder();
        sb.append("login ").append(credentials.getUsername()).append(" / ").append(credentials.getPassword());
        LogUtil.log(sb.toString(), Level.INFO, null);

//        UsernamePasswordToken token = new UsernamePasswordToken(credentials.getUsername(), credentials.getPassword());

//”Remember Me” built-in, just do this:
//        token.setRememberMe(true);

        //With most of Shiro, you'll always want to make sure you're working with the currently executing user, referred to as the subject
//        Subject currentUser = SecurityUtils.getSubject();

//Authenticate the subject by passing
//the user name and password token
//into the login method
        /*try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            JsfUtil.addErrorMessage(uae, "UnknownAccountException");

        } catch (IncorrectCredentialsException ice) {
            JsfUtil.addErrorMessage(ice, "IncorrectCredentialsException");

        } catch (LockedAccountException lae) {
            JsfUtil.addErrorMessage(lae, "LockedAccountException");

        } catch (ExcessiveAttemptsException eae) {
            JsfUtil.addErrorMessage(eae, "ExcessiveAttemptsException");

        } catch (AuthenticationException ae) {
            //unexpected error?
            JsfUtil.addErrorMessage(ae, "AuthenticationException");
        }*/
//No problems, show authenticated view…

        FamUser user = userManager.login(credentials.getUsername(), credentials.getPassword());
        if (user != null) {
            this.currentUser = user;
            JsfUtil.addSuccessMessage("Welcome, " + currentUser.getDisplayName());
        } else {
            JsfUtil.addErrorMessage("Login failed, no match for login/password");
            return null;
        }

        return prepareMyAccount();
    }

    public String logout() {
        JsfUtil.addSuccessMessage("Goodbye, " + currentUser.getDisplayName());
        currentUser = null;
        return home();
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Produces
    @LoggedIn
    @Named(value = "currentUser")
    public FamUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FamUser currentUser) {
        this.currentUser = currentUser;
    }

    // ============= OPENID ===============================
    public void signupOpenidGoogle() throws IOException {
        this.signupOpenid(GOOGLE_ENDPOINT);
    }

    public void signinOpenidGoogle() throws IOException {
        this.signinOpenid(GOOGLE_ENDPOINT);
    }

    public void signupOpenidYahoo() throws IOException {
        this.signupOpenid(YAHOO_ENDPOINT);
    }

    public void signinOpenidYahoo() throws IOException {
        this.signinOpenid(YAHOO_ENDPOINT);
    }

    public void signinOpenid(String endPoint) throws IOException {
        signup = Boolean.FALSE;
        userSuppliedId = endPoint;
        loginOpenId();
    }

    public void signupOpenid(String endPoint) throws IOException {
        signup = Boolean.FALSE;
        userSuppliedId = endPoint;
        loginOpenId();
    }

    public void loginOpenId() throws IOException {
//        System.out.println("login");
        try {
            manager = new ConsumerManager();
        } catch (ConsumerException e) {
            LogUtil.log("Error creating ConsumerManager.", Level.SEVERE, e);
        }
        validatedId = null;

        String returnUrl = new String();
        if (signup) {
            returnUrl = AUTH_SIGNUP_SUCCEED;
        } else {
            returnUrl = AUTH_SUCCEED;
        }

        String returnToUrl = returnToUrl(returnUrl);
        String url = authRequest(returnToUrl);

        if (url != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(AUTH_FAILED);
        }
    }

    /**
     * Create the current url and add another url path fragment on it.
     * Obtain from the current context the url and add another url path fragment at
     * the end
     *
     * @param urlExtension f.e. /nextside.xhtml
     * @return the hole url including the new fragment
     */
    private String returnToUrl(String urlExtension) {
//        System.out.println("returnToUrl");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LogUtil.log("returnToUrl", Level.SEVERE, ex);
//            Logger.getLogger(Openid.class.getName()).log(Level.SEVERE, null, ex);
        }
        String returnToUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                + context.getApplication().getViewHandler().getActionURL(context, urlExtension);
        return returnToUrl;
    }

    /**
     * Create an authentication request.
     * It performs a discovery on the user-supplied identifier. Attempt it to
     * associate with the OpenID provider and retrieve one service endpoint
     * for authentication. It adds some attributes for exchange on the AuthRequest.
     * A List of all possible attributes can be found on @see http://www.axschema.org/types/
     *
     * @param returnToUrl
     * @return the URL where the message should be sent
     * @throws IOException
     */
    private String authRequest(String returnToUrl) throws IOException {
//        System.out.println("authRequest");
        LogUtil.log("authRequest " + userSuppliedId, Level.INFO, null);
        LogUtil.log("cred.username " + credentials.getUsername(), Level.INFO, null);
        try {
            List discoveries = manager.discover(userSuppliedId);
            discovered = manager.associate(discoveries);
            AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

            FetchRequest fetch = FetchRequest.createFetchRequest();
//            if (userSuppliedId.contains("myopenid")) {
            fetch.addAttribute("email", "http://axschema.org/contact/email", true);
            fetch.addAttribute("firstname", "http://axschema.org/namePerson/first", true);
            fetch.addAttribute("lastname", "http://axschema.org/namePerson/last", true);
//            fetch.addAttribute("openid.ax.type.firstname",
//                    "http://axschema.org/namePerson/first", true);
//            fetch.addAttribute("openid.ax.type.lastname",
//                    "http://axschema.org/namePerson/last", true);

//            fetch.addAttribute("email",
//                    "http://schema.openid.net/contact/email", true);
//            fetch.addAttribute("namePerson",
//                    "http://schema.openid.net/contact/namePerson", true);


            authReq.addExtension(fetch);
            return authReq.getDestinationUrl(true);
        } catch (OpenIDException e) {
            // TODO
        }
        return null;
    }

    public void verify() {
//        System.out.println("verify");
        ExternalContext context = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        validatedId = verifyResponse(request);
    }

    /**
     * Set the class members with date from the authentication response.
     * Extract the parameters from the authentication response (which comes
     * in as a HTTP request from the OpenID provider). Verify the response,
     * examine the verification result and extract the verified identifier.
     *
     * @param httpReq httpRequest
     * @return users identifier.
     */
    private String verifyResponse(HttpServletRequest httpReq) {
//        System.out.println("verifyResponse");
        try {
            ParameterList response = new ParameterList(httpReq.getParameterMap());

            StringBuffer receivingURL = httpReq.getRequestURL();
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                receivingURL.append("?").append(httpReq.getQueryString());
            }

            VerificationResult verification = manager.verify(
                    receivingURL.toString(),
                    response, discovered);

            Identifier verified = verification.getVerifiedId();
            if (verified != null) {
//                System.out.println("verified");
                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();

                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);

                    List emails = fetchResp.getAttributeValues("email");
                    openIdEmail = (String) emails.get(0);
                    List firsts = fetchResp.getAttributeValues("firstname");
                    openIdFirstName = (String) firsts.get(0);
                    List lasts = fetchResp.getAttributeValues("lastname");
                    openIdLastName = (String) lasts.get(0);

                    currentUser = new FamUser();
                    currentUser.setEmail(openIdEmail);
                    currentUser.setFirstName(openIdFirstName);
                    currentUser.setLastName(openIdLastName);
                    currentUser.setOpenid(Boolean.TRUE);

                    if (this.signup) {
                        // create new User
                        doSignUp();
//                        userManager.create(user);
                        // TODO Catch exception(doublon)
//                        profileController.setFamUser(user);
//                        profileController.afterSignup();
                    } else {
                        // login
                        List<FamUser> lst = userManager.findByEmailAndOpenid(openIdEmail);
                        if (lst.isEmpty()) {
                            // user not found!
                            // throw Error!
                            LogUtil.log("Signin with openid - user not found", Level.SEVERE, null);
                        } else {
                            currentUser = lst.get(0);
                            LogUtil.log("Signin with openid - user " + currentUser, Level.INFO, null);
//                            profileController.afterSignin();
                        }
                    }
                }
                return verified.getIdentifier();
            }
        } catch (OpenIDException e) {
            // TODO
            LogUtil.log("OpenIDException", Level.SEVERE, e);
        }
        return null;
    }

    /**
     * hidden member for onLoad/Init event.
     *
     * @return always return the string pageLoaded
     */
    public String getOnLoad() {
//        System.out.println("getOnLoad");
        verify();
        return "pageLoaded?faces-redirect=true";
    }

    public String home() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String getOpenIdEmail() {
        return openIdEmail;
    }

    public String getOpenIdFirstName() {
        return openIdFirstName;
    }

    public String getOpenIdLastName() {
        return openIdLastName;
    }

    public String getUserSuppliedId() {
        return userSuppliedId;
    }

    public String doSignUp() {
        if (currentUser == null) {
            // Signup classique, si OpenId currentUser not null
            currentUser = new FamUser();
            currentUser.setEmail(credentials.getUsername());
            currentUser.setPassword(credentials.getPassword());
        }
        userManager.create(currentUser);
        return prepareMyAccount();
    }

    public String prepareMyAccount() {
//        reloadUser();
        return "pretty:myAccount";
    }

    public void reloadUser() {
        // on recharge le user
        if (currentUser != null) {
            currentUser = userManager.find(currentUser.getIdUser());
        } else {
            JsfUtil.addErrorMessage("Current user is null!");
        }

    }

    public String update() {
        userManager.edit(currentUser);
        return prepareMyAccount();
    }
}
