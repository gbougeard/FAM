package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTypPlace;
import org.fam.ejb.session.FamTypPlaceFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypPlaceController")
@ViewScoped
public class FamTypPlaceController extends AbstractController<FamTypPlace> implements Serializable {

    @EJB
    private FamTypPlaceFacade ejbFacade;

    public FamTypPlaceController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamTypPlace getSelected() {
        if (current == null) {
            current = new FamTypPlace();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypPlaceFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypPlace();
        return "pretty:editTypPlace";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypPlace();
        return "pretty:viewTypPlace";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypPlace";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypPlace();
        selectedItemIndex = -1;
        return "pretty:createTypPlace";
    }
}
