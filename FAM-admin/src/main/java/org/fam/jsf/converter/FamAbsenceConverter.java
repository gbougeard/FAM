/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamAbsence;
import org.fam.jsf.controller.FamAbsenceController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamAbsence.class, value = "absenceConverter")
public class FamAbsenceConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamAbsenceController controller = (FamAbsenceController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famAbsenceController");
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
        if (object instanceof FamAbsence) {
            FamAbsence o = (FamAbsence) object;
            return o.getIdAbsence() == null ? "" : o.getIdAbsence().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.FamAbsence");
        }
    }
}
