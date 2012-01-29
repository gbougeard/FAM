package org.fam.jsf.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 28/01/12
 * Time: 00:00
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 20111020L;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSettings.class);

    private List<Theme> availableThemes;
    private Theme currentTheme;

    public UserSettings() {
        currentTheme = AvailableThemes.getInstance().getThemeForName("home");
        availableThemes = AvailableThemes.getInstance().getThemes();
    }

    public List<Theme> getAvailableThemes() {
        return availableThemes;
    }

    public void setAvailableThemes(List<Theme> availableThemes) {
        this.availableThemes = availableThemes;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme currentTheme) {
        LOGGER.info("OLD theme : " + currentTheme.getName());
        this.currentTheme = currentTheme;
        LOGGER.info("NEW theme : " + this.currentTheme.getName());
    }
}
