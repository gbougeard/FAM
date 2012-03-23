/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamTeam;
import org.fam.jsf.controller.FamTeamController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamTeam.class, value = "teamConverter")
public class TeamConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamTeamController controller = (FamTeamController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famTeamController");
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
        if (object instanceof FamTeam) {
            FamTeam o = (FamTeam) object;
            return o.getIdTeam() == null ? "" : o.getIdTeam().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.FamTeam");
        }
    }
}
