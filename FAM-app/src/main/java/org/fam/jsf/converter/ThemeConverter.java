package org.fam.jsf.converter;

import org.fam.jsf.bean.AvailableThemes;
import org.fam.jsf.bean.Theme;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/01/12
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
@FacesConverter("org.fam.jsf.converter.converter.ThemeConverter")
public class ThemeConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
        return AvailableThemes.getInstance().getThemeForName(value);
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return ((Theme) value).getName();
    }
}