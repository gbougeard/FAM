/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Data;
import org.fam.common.cdi.LoggedIn;
import org.fam.common.cdi.Player;
import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamPlayerFacade;
import org.fam.ejb.session.FamUserFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.slf4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

/**
 * @author mask_hot
 */
@SessionScoped
@ManagedBean
@Data
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 3944588008523149795L;

    @Inject
    private Logger LOGGER;

//    @Resource
//    private SessionContext sessionContext;

    @Inject
    private FamUserFacade ejbUser;
    @Inject
    private FamPlayerFacade ejbPlayer;

    private FamUser currentUser;
    private FamPlayer currentPlayer;

    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
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

    @Produces
    @Player
    @Named(value = "currentPlayer")
    public FamPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private void getFamUser(String username, boolean isOpenId) {

        List<FamUser> users;
        if (isOpenId) {
            users = ejbUser.findByEmailAndOpenid(username);
        } else {
            users = ejbUser.findByEmail(username);
        }
        if (users != null && users.size() == 1) {
            this.currentUser = users.get(0);
            JsfUtil.addSuccessMessage("Welcome, " + currentUser.getDisplayName());

            List<FamPlayer> players = ejbPlayer.findByFamUser(this.currentUser);
            if (players.isEmpty()) {
                players = ejbPlayer.findPossiblePlayers(this.currentUser);
            }
            if (players.isEmpty()) {
                JsfUtil.addInfoMessage("Aucun joueur associé", "No Player");
            } else if (players.size() > 1) {
                JsfUtil.addInfoMessage("Plusieurs jouers possibles", "Too many players");
            } else {
                this.currentPlayer = players.get(0);
            }

        } else if (users != null && users.size() > 1) {
            JsfUtil.addErrorMessage("Login failed, find more than 1 user");
        } else if (users == null || users.isEmpty()) {
            JsfUtil.addErrorMessage("Login failed, cannot find user");
        }

    }

    public String login() {
        String message = "";
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {

            //Login via the Servlet Context
            request.login(username, password);

            //Retrieve the Principal
            Principal principal = request.getUserPrincipal();

            getFamUser(username, false);

            //Display a message based on the User role
            if (request.isUserInRole("Administrator")) {
                message = "Username : " + principal.getName() + " You are an Administrator, you can really f**k things up now";
            } else if (request.isUserInRole("Manager")) {
                message = "Username : " + principal.getName() + " You are only a Manager, Don't you have a Spreadsheet to be working on??";
            } else if (request.isUserInRole("Guest")) {
                message = "Username : " + principal.getName() + " You're wasting my resources...";
            }

            //Add the welcome message to the faces context
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            JsfUtil.addInfoMessage(message, "Welcome!");
//            return "";
            return "pretty:myAccount";
        } catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occured: Login failed", null));
            JsfUtil.addErrorMessage("An Error Occured: Login failed");
            LOGGER.error("login failed", e);
        }
        return "";
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        currentUser = null;
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
    }


}
