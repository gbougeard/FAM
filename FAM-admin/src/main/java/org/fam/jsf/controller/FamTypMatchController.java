package org.fam.jsf.controller;

import org.fam.ejb.model.FamTypMatch;
import org.fam.ejb.session.FamTypMatchFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "famTypMatchController")
@ViewScoped
public class FamTypMatchController extends AbstractController<FamTypMatch> implements Serializable {

    @Inject
    private FamTypMatchFacade ejbFacade;

    public FamTypMatchController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamTypMatch getSelected() {
        if (current == null) {
            current = new FamTypMatch();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypMatchFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypMatch();
        return "pretty:editTypMatch";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypMatch();
        return "pretty:viewTypMatch";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypMatch";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypMatch();
        selectedItemIndex = -1;
        return "pretty:createTypMatch";
    }
}
