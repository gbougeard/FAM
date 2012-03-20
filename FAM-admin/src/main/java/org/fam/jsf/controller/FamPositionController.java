package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPosition;
import org.fam.ejb.session.FamPositionFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famPositionController")
@ViewScoped
public class FamPositionController extends AbstractController<FamPosition> implements Serializable {

    @EJB
    private FamPositionFacade ejbFacade;

    public FamPositionController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
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
