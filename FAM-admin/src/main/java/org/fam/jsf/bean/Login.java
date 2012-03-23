/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.LoggedIn;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamUserFacade;
import org.fam.jsf.bean.util.JsfUtil;
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
import org.slf4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author mask_hot
 */
@SessionScoped
@Named
@Getter
@Setter
public class Login implements Serializable {

    private static final long serialVersionUID = 7965455427888195913L;
    //
    @Inject
    private Logger LOGGER;
    //
    @Inject
    private FamCredentials famCredentials;
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
        sb.append("login ").append(famCredentials.getUsername()).append(" / ").append(famCredentials.getPassword());

        FamUser user = userManager.login(famCredentials.getUsername(), famCredentials.getPassword());
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
        try {
            manager = new ConsumerManager();
        } catch (ConsumerException e) {
            LOGGER.error("Cannot Login with openid", e);
        }
        validatedId = null;

        String returnUrl = new String();
        if (signup) {
            returnUrl = AUTH_SIGNUP_SUCCEED;
        } else {
            returnUrl = AUTH_SUCCEED;
        }

        String returnToUrl = returnToUrl(returnUrl);
//        String url = authRequest(returnToUrl);
//        authRequest(returnToUrl);

//        if (url != null) {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
//        } else {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(AUTH_FAILED);
//        }
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
//            Logger.getLogger(Openid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "http://" + request.getServerName() + ":" + request.getServerPort()
                + context.getApplication().getViewHandler().getActionURL(context, urlExtension);
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
    /*private String authRequest(String returnToUrl) throws IOException {


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
    }*/
    public void verify() {
//        System.out.println("verify");
        ExternalContext context = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
//        validatedId = verifyResponse(request);
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
    /*private String verifyResponse(HttpServletRequest httpReq) {
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

                        } else {
                            currentUser = lst.get(0);

//                            profileController.afterSignin();
                        }
                    }
                }
                return verified.getIdentifier();
            }
        } catch (OpenIDException e) {
            // TODO

        }
        return null;
    }*/

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


    public String doSignUp() {
        if (currentUser == null) {
            // Signup classique, si OpenId currentUser not null
            currentUser = new FamUser();
            currentUser.setEmail(famCredentials.getUsername());
            currentUser.setPassword(famCredentials.getPassword());
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


    // --- placing the authentication request ---
    public String authRequest(String userSuppliedString,
                              HttpServletRequest httpReq,
                              HttpServletResponse httpResp)
            throws IOException {
        try {
            // configure the return_to URL where your application will receive
            // the authentication responses from the OpenID provider
            String returnToUrl = AUTH_SUCCEED; //"http://example.com/openid";

            // --- Forward proxy setup (only if needed) ---
            // ProxyProperties proxyProps = new ProxyProperties();
            // proxyProps.setProxyName("proxy.example.com");
            // proxyProps.setProxyPort(8080);
            // HttpClientFactory.setProxyProperties(proxyProps);

            // perform discovery on the user-supplied identifier
            List discoveries = manager.discover(userSuppliedString);

            // attempt to associate with the OpenID provider
            // and retrieve one service endpoint for authentication
            DiscoveryInformation discovered = manager.associate(discoveries);

            // store the discovery information in the user's session
            httpReq.getSession().setAttribute("openid-disc", discovered);

            // obtain a AuthRequest message to be sent to the OpenID provider
            AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

            // Attribute Exchange example: fetching the 'email' attribute
            FetchRequest fetch = FetchRequest.createFetchRequest();
            fetch.addAttribute("email",
                    // attribute alias
                    "http://schema.openid.net/contact/email",   // type URI
                    true);                                      // required

            // attach the extension to the authentication request
            authReq.addExtension(fetch);


            if (!discovered.isVersion2()) {
                // Option 1: GET HTTP-redirect to the OpenID Provider endpoint
                // The only method supported in OpenID 1.x
                // redirect-URL usually limited ~2048 bytes
                httpResp.sendRedirect(authReq.getDestinationUrl(true));
                return null;
            } else {
                // Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)

                RequestDispatcher dispatcher = httpReq.getServletContext().getRequestDispatcher("formredirection.jsp");
                httpReq.setAttribute("parameterMap", authReq.getParameterMap());
                httpReq.setAttribute("destinationUrl", authReq.getDestinationUrl(false));
                try {
                    dispatcher.forward(httpReq, httpResp);
                } catch (ServletException e) {
                    LOGGER.error("Error forwarding", e);
                }
            }
        } catch (OpenIDException e) {
            // present error to the user
        }

        return null;
    }

    // --- processing the authentication response ---
    public Identifier verifyResponse(HttpServletRequest httpReq) {
        try {
            // extract the parameters from the authentication response
            // (which comes in as a HTTP request from the OpenID provider)
            ParameterList response =
                    new ParameterList(httpReq.getParameterMap());

            // retrieve the previously stored discovery information
            DiscoveryInformation discovered = (DiscoveryInformation)
                    httpReq.getSession().getAttribute("openid-disc");

            // extract the receiving URL from the HTTP request
            StringBuffer receivingURL = httpReq.getRequestURL();
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                receivingURL.append("?").append(httpReq.getQueryString());
            }

            // verify the response; ConsumerManager needs to be the same
            // (static) instance used to place the authentication request
            VerificationResult verification = manager.verify(receivingURL.toString(),
                    response, discovered);

            // examine the verification result and extract the verified identifier
            Identifier verified = verification.getVerifiedId();
            if (verified != null) {
                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);

                    List emails = fetchResp.getAttributeValues("email");
//                    String email = (String) emails.get(0);
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

                }

                return verified;  // success
            }
        } catch (OpenIDException e) {
            // present error to the user
        }

        return null;
    }
}
