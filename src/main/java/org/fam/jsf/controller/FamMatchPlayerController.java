package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamMatchPlayer;
import org.fam.ejb.session.FamMatchPlayerFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famMatchPlayerController")
@ViewScoped
public class FamMatchPlayerController extends AbstractController<FamMatchPlayer> implements Serializable {

    @EJB
    private FamMatchPlayerFacade ejbFacade;


    public FamMatchPlayerController() {
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
    public FamMatchPlayer getSelected() {
        if (current == null) {
            current = new FamMatchPlayer();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamMatchPlayerFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        return "pretty:editMatchPlayer";
    }

    @Override
    public String prepareView() {
        return "pretty:viewMatchPlayer";
    }

    @Override
    public String prepareList() {
        return "pretty:listMatchPlayer";
    }


    @Override
    public String prepareCreate() {
        current = new FamMatchPlayer();
        selectedItemIndex = -1;
        return "pretty:createMatchPlayer";
    }


}
