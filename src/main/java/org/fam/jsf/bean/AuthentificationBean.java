/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamRole;
import org.primefaces.event.TabChangeEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.logging.Level;

/**
 * @author gbougear
 */
@Named
@SessionScoped
@Getter
@Setter
public class AuthentificationBean implements Serializable {

    //    @Resource
//    private SessionContext sessionContext;
    @Inject
    private SignupBean signupBean;
    @Inject
    private LoginBean loginBean;
    private int activeTab = 0;

    /**
     * Creates a new instance of AuthentificationBean
     */
    public AuthentificationBean() {
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        try {
//            request.login(this.loginBean.getEmail(), this.loginBean.getPassword());
////            this.signupBean = userDAO.find(this.username, this.password);
////            return "/"+this.signupBean.getLogin()+"/index?faces-redirect=true";
//            
//        } catch (ServletException e) {
//            // Handle unknown username/password in request.loginBean().
//            context.addMessage(null, new FacesMessage(e.getMessage()));
//        }
        return null;
    }

    // deconnecter l’utilisateur
    public String deconnexion() {
        String page = "/authentification?faces-redirect=true";
        // informations d’authentification
        FacesContext context = FacesContext.getCurrentInstance();
        // recuperer l’objet request
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperer l’objet session
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//        try {
//            session.invalidate();
//            request.logout();
//        } catch (ServletException ex) {
//            context.addMessage(null, new FacesMessage(ex.getMessage()));
//            page = "/erreur?faces-redirect=true";
//        }
        return "index?faces-redirect=true";
    }

    public String navSignup() {
        LogUtil.log("navSignup", Level.INFO, null);
        return "pretty:signup";
    }

    public String navLogin() {
        LogUtil.log("navLogin", Level.INFO, null);
        return "/auth/login?faces-redirect=true";
    }

    public String doSignup() {
        LogUtil.log("do signup", Level.INFO, null);
        LogUtil.log(signupBean.toString(), Level.OFF, null);
        return "/auth/signup?faces-redirect=true";
    }

    public String submit() {
        return null;
    }

    public String loginWithGoogle() {
        return null;
    }

    public String loginWithYahoo() {
        return null;
    }

    public Boolean getIsAdmin() {
//        return sessionContext.isCallerInRole(FamRole.ADMIN_SITE.name());
        FacesContext context = FacesContext.getCurrentInstance();
        // recuperer l’objet request
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole(FamRole.ADMIN_SITE.name());
    }

    public Boolean getIsAdminSite() {
        FacesContext context = FacesContext.getCurrentInstance();
        // recuperer l’objet request
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole(FamRole.ADMIN_SITE.name());
    }

    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
