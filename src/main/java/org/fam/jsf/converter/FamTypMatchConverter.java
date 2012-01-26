/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamTypMatch;
import org.fam.jsf.controller.FamTypMatchController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamTypMatch.class)
public class FamTypMatchConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        LogUtil.log(this.getClass() + "::getAsObject " + value, Level.INFO, null);
        if (value == null || value.length() == 0) {
            return null;
        }
        FamTypMatchController controller = (FamTypMatchController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famTypMatchController");
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
        if (object instanceof FamTypMatch) {
            FamTypMatch o = (FamTypMatch) object;
            return getStringKey(o.getIdTypMatch());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypMatchController.class.getName());
        }
    }
}