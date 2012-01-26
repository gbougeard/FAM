package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamState;
import org.fam.ejb.session.FamStateFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famStateController")
@ViewScoped
public class FamStateController extends AbstractController<FamState>
        implements Serializable {

    @EJB
    private FamStateFacade ejbFacade;

    public FamStateController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamState getSelected() {
        if (current == null) {
            current = new FamState();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamStateFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdState();
        return "pretty:editState";
    }

    @Override
    public String prepareView() {
        id = current.getIdState();
        return "pretty:viewState";
    }

    @Override
    public String prepareList() {
        return "pretty:listState";
    }

    @Override
    public String prepareCreate() {
        current = new FamState();
        selectedItemIndex = -1;
        return "pretty:createState";
    }
}