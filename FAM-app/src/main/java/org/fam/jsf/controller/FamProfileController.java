package org.fam.jsf.controller;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamProfile;
import org.fam.ejb.session.FamProfileFacade;

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
import java.util.logging.Level;

@Named
@SessionScoped
public class FamProfileController extends AbstractController<FamProfile> implements Serializable {

    @EJB
    private FamProfileFacade ejbFacade;

    public FamProfileController() {
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
    public FamProfile getSelected() {
        if (current == null) {
            current = new FamProfile();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamProfileFacade getFacade() {
        return ejbFacade;
    }


    @Override
    public String prepareCreate() {
        current = new FamProfile();
        selectedItemIndex = -1;
        return super.prepareCreate();
    }


    @FacesConverter(forClass = FamProfile.class)
    public static class FamProfileControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamProfileController controller = (FamProfileController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famProfileController");
            return controller.ejbFacade.find(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FamProfile) {
                FamProfile o = (FamProfile) object;
                return getStringKey(o.getIdProfile());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamProfileController.class.getName());
            }
        }
    }
}
