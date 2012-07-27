/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamTeam;
import org.fam.jsf.bean.TeamComposition;
import org.fam.jsf.controller.FamMatchController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author mask_hot
 */
@FacesConverter(forClass = TeamComposition.class, value = "teamCompositionConverter")
public class TeamCompositionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        FamMatchController controller = (FamMatchController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "famMatchController");
        return (controller.getLstTeamComposition().get(0).getFamTeam().getLibTeam().equals(value))?
                controller.getLstTeamComposition().get(0) : controller.getLstTeamComposition().get(1);
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || object.equals("")) {
            return null;
        }
        if (object instanceof TeamComposition) {
            TeamComposition o = (TeamComposition) object;
            return o.getFamTeam().getLibTeam() == null ? "" : o.getFamTeam().getLibTeam();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.TeamComposition");
        }
    }
}
