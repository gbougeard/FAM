/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.model.FamSeason;
import org.fam.jsf.controller.FamSeasonController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 * @author gregory.bougeard
 */
@FacesConverter(forClass = FamSeason.class)
public class FamSeasonConverter extends AbstractConverter<FamSeasonController> {

//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }
//
//        FamSeasonController controller = (FamSeasonController) facesContext.getApplication().getELResolver().
//                getValue(facesContext.getELContext(), null, "famSeasonController");
//        return controller.getEjbFacade().find(getKey(value));
////        Long key = getKey(value);
////        Object o = org.fam.ejb.find(key);
////        return o;
//    }
//
//    Long getKey(String value) {
//        Long key;
//        key = Long.valueOf(value);
//        return key;
//    }
//
//    String getStringKey(Long value) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(value);
//        return sb.toString();
//    }

    public FamSeasonConverter() {
        super(FamSeasonController.class);
    }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || object.equals("")) {
            return null;
        }
        if (object instanceof FamSeason) {
            FamSeason o = (FamSeason) object;
            return getStringKey(o.getIdSeason());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamSeasonController.class.getName());
        }
    }
}