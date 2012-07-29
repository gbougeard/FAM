/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamMatchTeam;
import org.fam.jsf.controller.FamMatchComposeWizardController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = FamMatchTeam.class, value = "matchTeamCompositionConverter")
public class MatchTeamCompositionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamMatchComposeWizardController controller = (FamMatchComposeWizardController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famMatchComposeWizardController");
        return (controller.getSelected().getFamMatchTeamList().get(0).getFamTeam().getLibTeam().equals(value)) ?
                controller.getSelected().getFamMatchTeamList().get(0) : controller.getLstTeamComposition().get(1);
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || object.equals("")) {
            return null;
        }
        if (object instanceof FamMatchTeam) {
            FamMatchTeam o = (FamMatchTeam) object;
            return o.getFamTeam().getLibTeam() == null ? "" : o.getFamTeam().getLibTeam();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.FamMatchTeam");
        }
    }
}
