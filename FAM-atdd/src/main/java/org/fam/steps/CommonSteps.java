package org.fam.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.fam.pages.HomePage;
import org.joda.time.DateTime;

import java.util.Date;

import static net.thucydides.core.pages.PageObject.withParameters;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 04/06/12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class CommonSteps extends ScenarioSteps {

    public CommonSteps(Pages pages) {

        super(pages);
    }

    private HomePage onHomePage() {

        return getPages().get(HomePage.class);
    }


    @Step
    public void open_home_page() {

        onHomePage().open("home");
    }


    @Step
    public void click_menu_club() {

        onHomePage().clickMenuClub();
    }

}
