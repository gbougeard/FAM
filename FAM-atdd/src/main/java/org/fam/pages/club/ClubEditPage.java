package org.fam.pages.club;

import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
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
//@At(urls = {"#HOST/fam/avail-info.php"})
@NamedUrls(
              {
                  @NamedUrl(name = "club.fichehotel", url = "http://prodrit/fam/reserhotel.php?lang=FR&id={2}&action=fiche_htl&hotelid={1}")
              }
)
public class ClubEditPage extends PageObject {

    @FindBy(how = How.CSS, using = "img[alt=\"RESERVEZ\"]")
    private WebElement continueBtn;


    public ClubEditPage(WebDriver driver) {

        super(driver);
    }

    public void next() {

        continueBtn.click();
    }

}
