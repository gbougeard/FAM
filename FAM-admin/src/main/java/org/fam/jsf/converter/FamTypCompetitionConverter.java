/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamTypCompetition;
import org.fam.jsf.controller.FamTypCompetitionController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamTypCompetition.class, value = "typCompetitionConverter")
public class FamTypCompetitionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        FamTypCompetitionController controller = (FamTypCompetitionController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famTypCompetitionController");
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
        if (object instanceof FamTypCompetition) {
            FamTypCompetition o = (FamTypCompetition) object;
            return getStringKey(o.getIdTypCompetition());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamTypCompetitionController.class.getName());
        }
    }
}
