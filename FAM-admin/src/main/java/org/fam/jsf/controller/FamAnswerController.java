package org.fam.jsf.controller;

import lombok.Getter;
import lombok.Setter;
import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.session.FamAnswerFacade;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "famAnswerController")
@ViewScoped
@Loggable
@Getter
@Setter
public class FamAnswerController extends AbstractController<FamAnswer> {

    @Inject
    private Logger LOGGER;
    @Inject
    private FamAnswerFacade ejbFacade;

    public FamAnswerController() {
    }

    @PostConstruct
    private void postConstruct() {
    }

    @PreDestroy
    private void preDestroy() {
    }

    @Override
    public FamAnswer getSelected() {
        if (current == null) {
            current = new FamAnswer();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamAnswerFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdAnswer();
        return "pretty:editAnswer";
    }

    @Override
    public String prepareView() {
        id = current.getIdAnswer();
        return "pretty:viewAnswer";
    }

    @Override
    public String prepareList() {
        return "pretty:listAnswer";
    }

    @Override
    public String prepareCreate() {
        current = new FamAnswer();
        selectedItemIndex = -1;
        return "pretty:createAnswer";
    }
}
