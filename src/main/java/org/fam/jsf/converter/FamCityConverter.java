/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamCity;
import org.fam.jsf.controller.FamCityController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamCity.class, value = "cityConverter")
public class FamCityConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LogUtil.log(this.getClass() + "::getAsObject " + value, Level.OFF, null);
        FamCityController controller = (FamCityController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famCityController");
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
        if (object instanceof FamCity) {
            FamCity o = (FamCity) object;
            return o.getIdCity() == null ? "" : o.getIdCity().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.FamCity");
        }
    }
}
