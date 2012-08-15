package org.fam.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 19/03/12
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
@DefaultUrl("http://localhost:8080/FAM-admin/")
//@At(urls="#HOST/FAM-admin/")
public class HomePage extends PageObject {

    @FindBy(how = How.ID, using = "frmNav:btnAgenda")
    private WebElement btnAgenda;
    @FindBy(how = How.ID, using = "frmNav:btnClub")
    private WebElement btnClub;
    @FindBy(how = How.ID, using = "frmNav:btnCommon")
    private WebElement btnCommon;


    public HomePage(WebDriver driver) {

        super(driver);
    }


    public void clickMenuClub() {
        btnCommon.click();
        btnClub.click();
    }

    public void clickMenuAgenda() {
        btnAgenda.click();
    }
}
