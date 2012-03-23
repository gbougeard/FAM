package org.fam.jsf.controller;

import org.fam.ejb.model.FamTypAnswer;
import org.fam.ejb.session.FamTypAnswerFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famTypAnswerController")
@ViewScoped
public class FamTypAnswerController extends AbstractController<FamTypAnswer> implements Serializable {

    @EJB
    private FamTypAnswerFacade ejbFacade;

    public FamTypAnswerController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamTypAnswer getSelected() {
        if (current == null) {
            current = new FamTypAnswer();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypAnswerFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypAnswer();
        return "pretty:editTypAnswer";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypAnswer();
        return "pretty:viewTypAnswer";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypAnswer";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypAnswer();
        selectedItemIndex = -1;
        return "pretty:createTypAnswer";
    }
}
