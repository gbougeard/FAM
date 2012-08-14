package org.fam.pages.club;

import net.thucydides.core.annotations.At;
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
@At(urls = {"#HOST/club/"})
public class ClubListPage extends PageObject {


    public ClubListPage(WebDriver driver) {

        super(driver);
    }


}
