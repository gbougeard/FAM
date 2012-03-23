package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamAbsence;
import org.fam.ejb.session.AbstractFacade;
import org.fam.ejb.session.FamAbsenceFacade;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "famAbsenceController")
@ViewScoped
@Loggable
@Getter
@Setter
public class FamAbsenceController extends AbstractController<FamAbsence> {

    @Inject
    private Logger LOGGER;
    @Inject
    private FamAbsenceFacade ejbFacade;

    public FamAbsenceController() {
    }

    @PostConstruct
    private void postConstruct() {
    }

    @PreDestroy
    private void preDestroy() {
    }

    @Override
    public FamAbsence getSelected() {
        if (current == null) {
            current = new FamAbsence();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdAbsence();
        return "pretty:editAbsence";
    }

    @Override
    public String prepareView() {
        id = current.getIdAbsence();
        return "pretty:viewAbsence";
    }

    @Override
    public String prepareList() {
        return "pretty:listAbsence";
    }

    @Override
    public String prepareCreate() {
        current = new FamAbsence();
        selectedItemIndex = -1;
        return "pretty:createAbsence";
    }

    @Override
    public AbstractFacade getFacade() {
        return ejbFacade;
    }

}
