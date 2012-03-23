package org.fam.jsf.controller;

import org.fam.ejb.model.FamEventStatus;
import org.fam.ejb.session.FamEventStatusFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famEventStatusController")
@ViewScoped
public class FamEventStatusController extends AbstractController<FamEventStatus> implements Serializable {

    @EJB
    private FamEventStatusFacade ejbFacade;

    public FamEventStatusController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamEventStatus getSelected() {
        if (current == null) {
            current = new FamEventStatus();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamEventStatusFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdEventStatus();
        return "pretty:editEventStatus";
    }

    @Override
    public String prepareView() {
        id = current.getIdEventStatus();
        return "pretty:viewEventStatus";
    }

    @Override
    public String prepareList() {
        return "pretty:listEventStatus";
    }

    @Override
    public String prepareCreate() {
        current = new FamEventStatus();
        selectedItemIndex = -1;
        return "pretty:createEventStatus";
    }


    public FamEventStatusFacade getEjbFacade() {
        return ejbFacade;
    }
}
