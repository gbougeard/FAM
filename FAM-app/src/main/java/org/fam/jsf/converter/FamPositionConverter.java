/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPosition;
import org.fam.jsf.controller.FamPositionController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamPosition.class, value = "positionConverter")
public class FamPositionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamPositionController controller = (FamPositionController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famPositionController");
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
        if (object instanceof FamPosition) {
            FamPosition o = (FamPosition) object;
            return getStringKey(o.getIdPosition());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamPositionController.class.getName());
        }
    }
}