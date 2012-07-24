package org.fam.jsf.bean;

import lombok.Data;
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
@Named(value = "userSettings")
@SessionScoped
@Data
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 20111020L;

    @Inject
    private Logger LOGGER;

    private static final String DEFAULT_THEME = "home";
    private String currentTheme;

    public UserSettings() {
    }

    @PostConstruct
    public void init() {
        currentTheme = DEFAULT_THEME;
    }

}
