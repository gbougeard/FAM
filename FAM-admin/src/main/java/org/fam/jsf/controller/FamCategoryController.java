package org.fam.jsf.controller;

import org.fam.ejb.model.FamCategory;
import org.fam.ejb.session.FamCategoryFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "famCategoryController")
@ViewScoped
public class FamCategoryController extends AbstractController<FamCategory> implements Serializable {

    @EJB
    private FamCategoryFacade ejbFacade;

    public FamCategoryController() {
    }

    @PostConstruct
    private void postConstruct() {

    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamCategory getSelected() {
        if (current == null) {
            current = new FamCategory();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCategoryFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdCategory();
        return "pretty:editCategory";
    }

    @Override
    public String prepareView() {
        id = current.getIdCategory();
        return "pretty:viewCategory";
    }

    @Override
    public String prepareList() {
        return "pretty:listCategory";
    }

    @Override
    public String prepareCreate() {
        current = new FamCategory();
        selectedItemIndex = -1;
        return "pretty:createCategory";
    }
}
