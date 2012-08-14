package org.fam.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 19/03/12
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
//@DefaultUrl("http://localhost:8080/FAM-admin/")
@NamedUrl(name = "home", url="http://ks305091.kimsufi.com/FAM-admin/")
public class HomePage extends PageObject {

//    @FindBy(how = How.ID, using = "frmNav:btnAgenda")
//    private WebElement btnAgenda;
//    @FindBy(how = How.ID, using = "frmNav:btnClub")
//    private WebElement btnClub;



    public HomePage(WebDriver driver) {

        super(driver);
    }


    public void clickMenuClub() {
//        btnClub.click();
    }
}
