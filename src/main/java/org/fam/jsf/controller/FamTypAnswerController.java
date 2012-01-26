package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamTypAnswer;
import org.fam.ejb.session.FamTypAnswerFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypAnswerController")
@ViewScoped
public class FamTypAnswerController extends AbstractController<FamTypAnswer> implements Serializable {

    @EJB
    private FamTypAnswerFacade ejbFacade;

    public FamTypAnswerController() {
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
