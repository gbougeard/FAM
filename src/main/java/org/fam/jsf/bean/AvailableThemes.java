package org.fam.jsf.bean;


import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mask_hot
 * Date: 27/01/12
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
//@Named
public class AvailableThemes {
    private static AvailableThemes instance = null;

    public static AvailableThemes getInstance() {
        if (instance == null) {
            instance = new AvailableThemes();
        }

        return instance;
    }

    private final HashMap<String, Theme> themesAsMap;
    private final List<String> themeNames;
    private final List<Theme> themes;

    private AvailableThemes() {
        themeNames = new ArrayList<String>();
        themeNames.add("aristo");
        themeNames.add("black-tie");
        themeNames.add("blitzer");
        themeNames.add("bluesky");
        themeNames.add("casablanca");
        themeNames.add("cupertino");
        themeNames.add("dark-hive");
        themeNames.add("dot-luv");
        themeNames.add("eggplant");
        themeNames.add("excite-bike");
        themeNames.add("flick");
        themeNames.add("glass-x");
        themeNames.add("home");
        themeNames.add("hot-sneaks");
        themeNames.add("humanity");
        themeNames.add("le-frog");
        themeNames.add("midnight");
        themeNames.add("mint-choc");
        themeNames.add("overcast");
        themeNames.add("pepper-grinder");
        themeNames.add("redmond");
        themeNames.add("rocket");
        themeNames.add("sam");
        themeNames.add("smoothness");
        themeNames.add("south-street");
        themeNames.add("start");
        themeNames.add("swanky-purse");
        themeNames.add("trontastic");
        themeNames.add("ui-darkness");
        themeNames.add("ui-lightness");
        themeNames.add("vader");

        themesAsMap = new HashMap<String, Theme>();
        themes = new ArrayList<Theme>();

        for (final String themeName : themeNames) {
            final Theme theme = new Theme();
            theme.setName(themeName);
            theme.setImage("/images/themes/" + themeName + ".png");

            themes.add(theme);
            themesAsMap.put(theme.getName(), theme);
        }
    }

    public final List<Theme> getThemes() {
        return themes;
    }

    public Theme getThemeForName(final String name) {
        return themesAsMap.get(name);
    }
}
