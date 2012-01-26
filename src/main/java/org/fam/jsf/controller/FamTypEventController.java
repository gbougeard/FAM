package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamTypEvent;
import org.fam.ejb.session.FamTypEventFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypEventController")
@ViewScoped
public class FamTypEventController extends AbstractController<FamTypEvent> implements Serializable {

    @EJB
    private FamTypEventFacade ejbFacade;

    public FamTypEventController() {
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
    public FamTypEvent getSelected() {
        if (current == null) {
            current = new FamTypEvent();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypEventFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypEvent();
        return "pretty:editTypEvent";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypEvent();
        return "pretty:viewTypEvent";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypEvent";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypEvent();
        selectedItemIndex = -1;
        return "pretty:createTypEvent";
    }
}
