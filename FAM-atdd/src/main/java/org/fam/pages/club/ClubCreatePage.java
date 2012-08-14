package org.fam.pages.club;

import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 19/03/12
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
//@At(urls = {"#HOST/fam/avail-info.php"})
//@DefaultUrl("http://prodrit/fam/reserhotel.php?lang=FR&action=list-avail&ratetype=PRO&id={1}")
//@DefaultUrl("http://prodrit/jreservit/listrates.do?action=adv-avail&langcode=FR&partid=0&id={1}&httphost=prodrit&directoryJMANAGER=jreservit&applitype=client&zoneid=1&ratetype=PRO&mode=specialrate&searchcriteriaparam=ratetype%3APRO")
@NamedUrls(
              {
                  @NamedUrl(name = "club.promos", url = "http://prodrit/fam/reserhotel.php?lang=FR&action=list-avail&ratetype=PRO&id={1}")
              }
)
public class ClubCreatePage extends PageObject {

//    @FindBy(how = How.ID, using = "buttonbtm")
//    private WebElement rechercherBtn;


    public ClubCreatePage(WebDriver driver) {

        super(driver);
    }

    public void rechercher() {

//        rechercherBtn.click();
    }

}
