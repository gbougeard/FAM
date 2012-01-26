/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.converter;

import org.fam.ejb.common.LogUtil;
import org.fam.jsf.controller.AbstractController;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
public abstract class AbstractConverter<T> implements Converter {

    private Class<T> controllerClass;
    protected AbstractController controller;

    public AbstractConverter(Class<T> controllerClass) {
        this.controllerClass = controllerClass;
    }

    protected Long getKey(String value) {
        Long key;
        key = Long.valueOf(value);
        return key;
    }

    protected String getStringKey(Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LogUtil.log(this.getClass() + "::getAsObject " + value, Level.OFF, null);

        controller = (AbstractController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, controllerClass.getName());

        return controller.getFacade().find(getKey(value));
    }

//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null) {
//            return null;
//        }
//        if (object instanceof E) {
//            E o = (E) object;
//            return getStringKey(o.getIdSeason());
//        } else {
//            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + controllerClass.getName());
//        }
//    }
}
