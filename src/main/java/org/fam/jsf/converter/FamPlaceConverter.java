/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPlace;
import org.fam.jsf.controller.FamPlaceController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamPlace.class, value = "placeConverter")
public class FamPlaceConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LogUtil.log(this.getClass() + "::getAsObject " + value, Level.OFF, null);
        FamPlaceController controller = (FamPlaceController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famPlaceController");
        return controller.getFacade().find(getKey(value));
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
        if (object instanceof FamPlace) {
            FamPlace o = (FamPlace) object;
            return o.getIdPlace() == null ? "" : o.getIdPlace().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.FamPlace");
        }
    }
}
