package org.fam.pages.club;

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
//@At(urls = {"#HOST/fam/avail-info.php"})
@NamedUrls(
              {
                  @NamedUrl(name = "club.advsearch", url = "http://prodrit/fam/reserhotel.php?lang=FR&id={1}&action=adv-avail")
              }
)
public class ClubViewPage extends PageObject {

    @FindBy(how = How.ID, using = "namecity")
    private WebElement cityField;
    @FindBy(how = How.ID, using = "namehotel")
    private WebElement hotelField;
    @FindBy(how = How.NAME, using = "country")
    private WebElement countrySelect;
    @FindBy(how = How.NAME, using = "area")
    private WebElement areaSelect;
    @FindBy(how = How.NAME, using = "state")
    private WebElement stateSelect;
    @FindBy(how = How.NAME, using = "nbnight")
    private WebElement nbNightSelect;
    @FindBy(how = How.NAME, using = "numroom")
    private WebElement nbRoomSelect;
    @FindBy(how = How.NAME, using = "dayDateFrom")
    private WebElement dayFromSelect;
    @FindBy(how = How.NAME, using = "monthDateFrom")
    private WebElement monthFromSelect;
    @FindBy(how = How.NAME, using = "yearDateFrom")
    private WebElement yearFromSelect;
    @FindBy(how = How.NAME, using = "dayDateTo")
    private WebElement dayToSelect;
    @FindBy(how = How.NAME, using = "monthDateTo")
    private WebElement monthToSelect;
    @FindBy(how = How.NAME, using = "yearDateTo")
    private WebElement yearToSelect;
    @FindBy(how = How.ID, using = "chb_search_without_date")
    private WebElement withoutDateCheckBox;
    @FindBy(how = How.ID, using = "result_reco")
    private WebElement result_reco;


    @FindBy(how = How.ID, using = "bt_consulter")
    private WebElement rechercherBtn;

    public void selectSelectValues(WebElement element, String value) {

        element(element).selectByVisibleText(value);
        assertThat(element(element).getSelectedVisibleTextValue(), is(value));
    }


    public ClubViewPage(WebDriver driver) {

        super(driver);
    }

    public void rechercher() {

        rechercherBtn.click();
    }

    public void setCity(String value) {

        element(cityField).type(value);
    }


    public WebElementFacade getResultReco() {

        return element(result_reco);
    }
}
