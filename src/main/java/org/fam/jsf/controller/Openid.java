/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamUserFacade;
import org.fam.jsf.bean.Login;
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

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
@Named(value = "openid")
@SessionScoped
public class Openid implements Serializable {

    /**
     * Creates a new instance of Openid
     */
    public Openid() {
    }

    private String userSuppliedId; //Users OpenID URL
    private String validatedId;
    private String openIdEmail;
    private String openIdFullName;
    private String openIdFirstName;
    private String openIdLastName;
    private String openIdCountry;
    /* ... */
    private String onLoad;
    private ConsumerManager manager;
    private DiscoveryInformation discovered;
    private final static String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";
    private final static String YAHOO_ENDPOINT = "http://me.yahoo.com";
    //
    private final static String AUTH_SIGNUP_SUCCEED = "/callback.xhtml";
    //    private final static String AUTH_SIGNUP_FAILED = "/auth/loginError.xhtml";
    private final static String AUTH_SUCCEED = "/callback.xhtml";
    private final static String AUTH_FAILED = "/err/error.xhtml";
    //
    @EJB
    private FamUserFacade ejbFamUserFacade;
    // private 
    private Boolean signup = Boolean.TRUE;
    //
    @Inject
    ProfileController profileController;
    @Inject
    Login login;

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
        this.setUserSuppliedId(endPoint);
        login();
    }

    public void signupOpenid(String endPoint) throws IOException {
        signup = Boolean.FALSE;
        this.setUserSuppliedId(endPoint);
        login();
    }

    public void login() throws IOException {
//        System.out.println("login");
        try {
            manager = new ConsumerManager();
        } catch (ConsumerException e) {
//            System.out.println("Error creating ConsumerManager.");
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

                    FamUser user = new FamUser();
                    user.setEmail(openIdEmail);
                    user.setFirstName(openIdFirstName);
                    user.setLastName(openIdLastName);
                    user.setOpenid(Boolean.TRUE);

                    if (this.signup) {
                        // create new User
                        ejbFamUserFacade.create(user);
                        // TODO Catch exception(doublon)
                        profileController.setFamUser(user);
                        profileController.afterSignup();
                    } else {
                        // login
                        List<FamUser> lst = ejbFamUserFacade.findByEmailAndOpenid(openIdEmail);
                        if (lst.isEmpty()) {
                            // user not found!
                            // throw Error!
                            LogUtil.log("Signin with openid - user not found", Level.SEVERE, null);
                        } else {
                            user = lst.get(0);
                            LogUtil.log("Signin with openid - user " + user, Level.INFO, null);
                            profileController.setFamUser(user);
                            login.setCurrentUser(user);
                            profileController.afterSignin();
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
        return "/index.xhtml";
    }

    /**
     * Getter and Setter Method
     */
    public String getUserSuppliedId() {
        return userSuppliedId;
    }

    public void setUserSuppliedId(String userSuppliedId) {
        this.userSuppliedId = userSuppliedId;
    }

    public String getValidatedId() {
        return validatedId;
    }

    public String getOpenIdEmail() {
        return openIdEmail;
    }

    public String getOpenIdCountry() {
        return openIdCountry;
    }

    public void setOpenIdCountry(String openIdCountry) {
        this.openIdCountry = openIdCountry;
    }

    public String getOpenIdFirstName() {
        return openIdFirstName;
    }

    public void setOpenIdFirstName(String openIdFirstName) {
        this.openIdFirstName = openIdFirstName;
    }

    public String getOpenIdFullName() {
        return openIdFullName;
    }

    public void setOpenIdFullName(String openIdFullName) {
        this.openIdFullName = openIdFullName;
    }

    public String getOpenIdLastName() {
        return openIdLastName;
    }

    public void setOpenIdLastName(String openIdLastName) {
        this.openIdLastName = openIdLastName;
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}
