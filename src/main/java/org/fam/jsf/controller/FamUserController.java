package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamUser;
import org.fam.ejb.session.FamUserFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famUserController")
@ViewScoped
public class FamUserController extends AbstractController<FamUser> implements Serializable {

    @EJB
    private FamUserFacade ejbFacade;

    public FamUserController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamUser getSelected() {
        if (current == null) {
            current = new FamUser();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamUserFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdUser();
        return "pretty:editUser";
    }

    @Override
    public String prepareView() {
        id = current.getIdUser();
        return "pretty:viewUser";
    }

    @Override
    public String prepareList() {
        return "pretty:listUser";
    }

    @Override
    public String prepareCreate() {
        current = new FamUser();
        selectedItemIndex = -1;
        return "pretty:createUser";
    }
}
