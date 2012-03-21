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

//    private Theme currentTheme;
    private String currentTheme;

    public UserSettings() {
    }

    @PostConstruct
    public void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("init");
        }
//        currentTheme = availableThemes.getThemeForName(DEFAULT_THEME);
        currentTheme = DEFAULT_THEME;
    }


//    public Theme getCurrentTheme() {
//        return currentTheme;
//    }
//
//    public void setCurrentTheme(Theme currentTheme) {
//        LOGGER.warn(String.format("OLD theme : %s", currentTheme.getName()));
//        this.currentTheme = currentTheme;
//        LOGGER.warn(String.format("NEW theme : %s", this.currentTheme.getName()));
//    }
    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        LOGGER.warn(String.format("OLD theme : %s", currentTheme));
        this.currentTheme = currentTheme;
        LOGGER.warn(String.format("NEW theme : %s", this.currentTheme));
    }

    public void onChange(){
        LOGGER.warn("onChange()");
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }
}
