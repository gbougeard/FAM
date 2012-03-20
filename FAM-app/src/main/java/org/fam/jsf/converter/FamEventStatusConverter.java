/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamEventStatus;
import org.fam.jsf.controller.FamEventStatusController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author gregory.bougeard
 */
@FacesConverter(forClass = FamEventStatus.class)
public class FamEventStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        FamEventStatusController controller = (FamEventStatusController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famEventStatusController");
        return controller.getEjbFacade().find(getKey(value));
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
        if (object == null || object.equals("")) {
            return null;
        }
        if (object instanceof FamEventStatus) {
            FamEventStatus o = (FamEventStatus) object;
            return getStringKey(o.getIdEventStatus());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamEventStatusController.class.getName());
        }
    }
}