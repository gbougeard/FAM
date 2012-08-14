package org.fam.common;

import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 07/08/12
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static void selectByValue(WebElement selectElement, String value) {

        Select selectBox = new Select(selectElement);
        selectBox.selectByValue(value);
    }

    public static void selectByIndex(WebElement selectElement, int index) {

        Select selectBox = new Select(selectElement);
        selectBox.selectByIndex(index);
    }

    public static void selectByVisibleTexte(WebElement selectElement, String value) {

        Select selectBox = new Select(selectElement);
        selectBox.selectByVisibleText(value);
    }

    public static void deselectAll(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        selectBox.deselectAll();
    }

    public static void deselectByIndex(WebElement selectElement, int index) {

        Select selectBox = new Select(selectElement);
        selectBox.deselectByIndex(index);
    }

    public static void deselectByValue(WebElement selectElement, String value) {

        Select selectBox = new Select(selectElement);
        selectBox.deselectByValue(value);
    }

    public static void deselectByVisbileText(WebElement selectElement, String value) {

        Select selectBox = new Select(selectElement);
        selectBox.deselectByVisibleText(value);
    }

    public static List<WebElement> getAllOptions(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        return selectBox.getOptions();
    }

    public static List<WebElement> getAllSelectedOptions(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        return selectBox.getAllSelectedOptions();
    }

    public static WebElement getFirstSelectedOption(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        return selectBox.getFirstSelectedOption();
    }

    public static String getFirstSelectedOptionText(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        return selectBox.getFirstSelectedOption().getText();
    }

    public static Boolean isMultiple(WebElement selectElement) {

        Select selectBox = new Select(selectElement);
        return selectBox.isMultiple();
    }

    public static void initSelectDate(WebElement selectDay, WebElement selectMonth, WebElement selectYear, DateTime date) {

        Util.selectByValue(selectDay, String.valueOf(date.getDayOfMonth()));
        Util.selectByValue(selectMonth, String.valueOf(date.getMonthOfYear()));
        Util.selectByValue(selectYear, String.valueOf(date.getYear()));

    }
}
