package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamCountry;
import org.fam.ejb.session.FamCountryFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famCountryController")
@ViewScoped
public class FamCountryController extends AbstractController<FamCountry>
        implements Serializable {

    @EJB
    private FamCountryFacade ejbFacade;

    public FamCountryController() {
    }

    @PostConstruct
    private void postConstruct() {

//        findAll();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamCountry getSelected() {
        if (current == null) {
            current = new FamCountry();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCountryFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdCountry();
        return "pretty:editCountry";
    }

    @Override
    public String prepareView() {
        id = current.getIdCountry();
        return "pretty:viewCountry";
    }

    @Override
    public String prepareList() {
        return "pretty:listCountry";
    }

    @Override
    public String prepareCreate() {
        current = new FamCountry();
        selectedItemIndex = -1;
        return "pretty:createCountry";
    }

}
