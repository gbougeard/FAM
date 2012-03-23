/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamSeasonCompetition;
import org.fam.jsf.controller.FamSeasonCompetitionController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamSeasonCompetition.class, value = "competConverter")
public class FamSeasonCompetitionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamSeasonCompetitionController controller = (FamSeasonCompetitionController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "famSeasonCompetitionController");
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
        if (object instanceof FamSeasonCompetition) {
            FamSeasonCompetition o = (FamSeasonCompetition) object;
            return getStringKey(o.getIdSeasonCompetition());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamSeasonCompetitionController.class.getName());
        }
    }
}
