package org.fam.jsf.controller;

import org.fam.ejb.model.FamSeason;
import org.fam.ejb.session.FamSeasonFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famSeasonController")
@ViewScoped
public class FamSeasonController extends AbstractController<FamSeason> implements Serializable {

    @EJB
    private FamSeasonFacade ejbFacade;

    public FamSeasonController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamSeason getSelected() {
        if (current == null) {
            current = new FamSeason();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamSeasonFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdSeason();
        return "pretty:editSeason";
    }

    @Override
    public String prepareView() {
        id = current.getIdSeason();
        return "pretty:viewSeason";
    }

    @Override
    public String prepareList() {
        return "pretty:listSeason";
    }

    @Override
    public String prepareCreate() {
        current = new FamSeason();
        selectedItemIndex = -1;
        return "pretty:createSeason";
    }

    public FamSeasonFacade getEjbFacade() {
        return ejbFacade;
    }
}
