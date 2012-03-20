package org.fam.jsf.bean;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 28/01/12
 * Time: 00:00
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 20111020L;

    @Inject
    private Logger LOGGER;

    private static final String DEFAULT_THEME = "home";

    @Inject
    private AvailableThemes availableThemes;

//    private List<Theme> availableThemes;
    private Theme currentTheme;

    public UserSettings() {
//        availableThemes = AvailableThemes.getInstance();
//        currentTheme = availableThemes.getThemeForName(DEFAULT_THEME);
    }

    @PostConstruct
    public void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("init");
        }
        currentTheme = availableThemes.getThemeForName(DEFAULT_THEME);
    }


    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme currentTheme) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("OLD theme : %s", currentTheme.getName()));
        }
        this.currentTheme = currentTheme;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("NEW theme : %s", this.currentTheme.getName()));
        }
    }

    public void setAvailableThemes(AvailableThemes availableThemes) {
        this.availableThemes = availableThemes;
    }

    public AvailableThemes getAvailableThemes() {
        return availableThemes;
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }
}
