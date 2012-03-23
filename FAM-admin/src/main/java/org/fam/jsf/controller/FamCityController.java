package org.fam.jsf.controller;

import org.fam.ejb.model.FamCity;
import org.fam.ejb.session.FamCityFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famCityController")
@ViewScoped
public class FamCityController extends AbstractController<FamCity>
        implements Serializable {

    @EJB
    private FamCityFacade ejbFacade;

    public FamCityController() {
    }

    @PostConstruct
    private void postConstruct() {
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
    }

    @Override
    public FamCity getSelected() {
        if (current == null) {
            current = new FamCity();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCityFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdCity();
        return "pretty:editCity";
    }

    @Override
    public String prepareView() {
        id = current.getIdCity();
        return "pretty:viewCity";
    }

    @Override
    public String prepareList() {
        return "pretty:listCity";
    }

    @Override
    public String prepareCreate() {
        current = new FamCity();
        selectedItemIndex = -1;
        return "pretty:createCity";
    }

}
