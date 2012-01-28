package org.fam.jsf.bean;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

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

    private List<Theme> availableThemes;
    private Theme currentTheme;

    public UserSettings() {
        currentTheme = AvailableThemes.getInstance().getThemeForName("home");
        availableThemes = AvailableThemes.getInstance().getThemes();
    }

    public  List<Theme> getAvailableThemes() {
        return availableThemes;
    }

    public  void setAvailableThemes(List<Theme> availableThemes) {
        this.availableThemes = availableThemes;
    }

    public  Theme getCurrentTheme() {
        return currentTheme;
    }

    public  void setCurrentTheme(Theme currentTheme) {
        this.currentTheme = currentTheme;
    }
}
