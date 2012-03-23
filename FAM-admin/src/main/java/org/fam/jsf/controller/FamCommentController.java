package org.fam.jsf.controller;

import org.fam.ejb.model.FamComment;
import org.fam.ejb.session.FamCommentFacade;

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
public class FamCommentController extends AbstractController<FamComment> implements Serializable {

    @EJB
    private FamCommentFacade ejbFacade;

    public FamCommentController() {
    }

    @PostConstruct
    private void postConstruct() {
    }

    @PreDestroy
    private void preDestroy() {
    }

    @Override
    public FamComment getSelected() {
        if (current == null) {
            current = new FamComment();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamCommentFacade getFacade() {
        return ejbFacade;
    }


    @Override
    public String prepareCreate() {
        current = new FamComment();
        selectedItemIndex = -1;
        return super.prepareCreate();
    }


    @FacesConverter(forClass = FamComment.class)
    public static class FamCommentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamCommentController controller = (FamCommentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "famCommentController");
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
            if (object instanceof FamComment) {
                FamComment o = (FamComment) object;
                return getStringKey(o.getIdComment());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamCommentController.class.getName());
            }
        }
    }
}
