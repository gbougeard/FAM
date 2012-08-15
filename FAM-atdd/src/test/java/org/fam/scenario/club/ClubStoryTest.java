package org.fam.scenario.club;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.fam.App;
import org.fam.steps.CommonSteps;
import org.fam.steps.club.ClubSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 04/06/12
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
@WithTag(name = "club")
@RunWith(ThucydidesRunner.class)
//@RunWith(ThucydidesParameterizedRunner.class)
@Story(App.Club.class)
public class ClubStoryTest {

//    @TestData
//    public static Collection<Object[]> testData() {
//
//        return Arrays.asList(new Object[][]{   //{custId, hotelId}
////                                               {Constants.CUSTID_INTER_HOTEL, 10331},
////                                               {Constants.CUSTID_CHF, 4382},
//        });
//    }

//    private Integer hotelId;
//    private Integer custId;
//
//    public ClubStoryTest(Integer custId, Integer hotelId) {
//
//        this.custId = custId;
//        this.hotelId = hotelId;
//    }

    @Managed
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "#HOST/FAM-admin/")
    public Pages pages;

    @Steps
    public ClubSteps clubSteps;
    @Steps
    public CommonSteps commonSteps;

    @Test
    public void open_agenda(){
        commonSteps.click_menu_agenda();
    }

    @Test
    public void test1() {
//        commonSteps.open_home_page();
        commonSteps.click_menu_club();


//        clubSteps.open_booking_step1_page(this.custId, this.hotelId);
//        clubSteps.initStartDateAndNights(new DateTime().plusMonths(1).toDate(), 2);
//        clubSteps.searchBooking();
//        clubSteps.step2_booking();
//        clubSteps.step3_confirmBooking();
//        clubSteps.step4_acceptCGVAndOrder();
//        clubSteps.resumeBooking();
        //clubSteps.searchBookingInOneMonthForTwoNightsTwoAdults(this.custId, this.hotelId);
    }


}
