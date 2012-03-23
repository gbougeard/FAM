package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamOrganization;
import org.fam.ejb.session.FamOrganizationFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;

@ManagedBean(name = "famOrganizationController")
@ViewScoped
public class FamOrganizationController extends AbstractController<FamOrganization> implements Serializable {

    @EJB
    private FamOrganizationFacade ejbFacade;

    public FamOrganizationController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamOrganization getSelected() {
        if (current == null) {
            current = new FamOrganization();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamOrganizationFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdOrganization();
        return "pretty:editOrganization";
    }

    @Override
    public String prepareView() {
        id = current.getIdOrganization();
        return "pretty:viewOrganization";
    }

    @Override
    public String prepareList() {
        return "pretty:listOrganization";
    }

    @Override
    public String prepareCreate() {
        current = new FamOrganization();
        selectedItemIndex = -1;
        return "pretty:createOrganization";
    }
}
