package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamTypCompetition;
import org.fam.ejb.session.FamTypCompetitionFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famTypCompetitionController")
@ViewScoped
public class FamTypCompetitionController extends AbstractController<FamTypCompetition> implements Serializable {

    @EJB
    private FamTypCompetitionFacade ejbFacade;

    public FamTypCompetitionController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamTypCompetition getSelected() {
        if (current == null) {
            current = new FamTypCompetition();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamTypCompetitionFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdTypCompetition();
        return "pretty:editTypCompetition";
    }

    @Override
    public String prepareView() {
        id = current.getIdTypCompetition();
        return "pretty:viewTypCompetition";
    }

    @Override
    public String prepareList() {
        return "pretty:listTypCompetition";
    }

    @Override
    public String prepareCreate() {
        current = new FamTypCompetition();
        selectedItemIndex = -1;
        return "pretty:createTypCompetition";
    }


}
