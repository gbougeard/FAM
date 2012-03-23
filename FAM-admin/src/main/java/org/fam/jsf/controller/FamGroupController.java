package org.fam.jsf.controller;

import org.fam.ejb.model.FamGroup;
import org.fam.ejb.session.FamGroupFacade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class FamGroupController extends AbstractController<FamGroup> implements Serializable {

    @EJB
    private FamGroupFacade ejbFacade;

    public FamGroupController() {
    }

    @PostConstruct
    private void postConstruct() {

        findAll();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamGroup getSelected() {
        if (current == null) {
            current = new FamGroup();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamGroupFacade getFacade() {
        return ejbFacade;
    }


    @Override
    public String prepareCreate() {
        current = new FamGroup();
        selectedItemIndex = -1;
        return super.prepareCreate();
    }


    @FacesConverter(forClass = FamGroup.class)
    public static class FamGroupControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamGroupController controller = (FamGroupController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famGroupController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FamGroup) {
                FamGroup o = (FamGroup) object;
                return getStringKey(o.getIdGroup());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamGroupController.class.getName());
            }
        }
    }
}
