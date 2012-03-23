package org.fam.jsf.controller;

import org.fam.ejb.model.FamPosition;
import org.fam.ejb.session.FamPositionFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famPositionController")
@ViewScoped
public class FamPositionController extends AbstractController<FamPosition> implements Serializable {

    @EJB
    private FamPositionFacade ejbFacade;

    public FamPositionController() {
    }

    @PostConstruct
    private void postConstruct() {

//        findAll();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamPosition getSelected() {
        if (current == null) {
            current = new FamPosition();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamPositionFacade getFacade() {
        return ejbFacade;
    }


    @Override
    public String prepareCreate() {
        current = new FamPosition();
        selectedItemIndex = -1;
        return "pretty:createPosition";
    }


}
