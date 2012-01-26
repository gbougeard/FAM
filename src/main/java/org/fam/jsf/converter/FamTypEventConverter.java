/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.common.LogUtil;
import org.fam.ejb.model.FamTypEvent;
import org.fam.jsf.controller.FamTypEventController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author gregory.bougeard
 */
@FacesConverter(forClass = FamTypEvent.class)
public class FamTypEventConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LogUtil.log(this.getClass() + "::getAsObject " + value, Level.OFF, null);
        FamTypEventController controller = (FamTypEventController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famTypEventController");
        return controller.getFacade().find(getKey(value));
//        Long key = getKey(value);
//        Object o = org.fam.ejb.find(key);
//        return o;
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
        if (object instanceof FamTypEvent) {
            FamTypEvent o = (FamTypEvent) object;
            return getStringKey(o.getIdTypEvent());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypEventController.class.getName());
        }
    }
}
