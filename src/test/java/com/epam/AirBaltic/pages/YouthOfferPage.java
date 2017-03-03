package com.epam.AirBaltic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YouthOfferPage extends Page {

    private static final String YOUTH_OFFER_PAGE_URL = "https://www.airbaltic.com/youth-offer";

    private static final By SELECTED_DDMENU_ITEM = By.xpath("//span[@class='twitter-typeahead']/descendant::strong[@class='tt-highlight']");

    @FindBy(xpath = "//div[@class='input-group input-suggest-main dest-field-specific'][1]")
    private WebElement fMenuOrigins;

    @FindBy(xpath = "//input[@name='flt_origin_text']")
    private WebElement fMenuOrigins_1;

    @FindBy(xpath = "//div[@class='input-group input-suggest-main dest-field-specific'][1]/descendant::div[@class='tt-suggestion tt-selectable']")
    private List<WebElement> listDDMenuOrigins;

    @FindBy(xpath = "//div[@class='flight-line']/div[@class='origin']")
    private List<WebElement> listOfferOrigins;


    public YouthOfferPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void Open() {
        driver.navigate().to(YOUTH_OFFER_PAGE_URL);
    }

    private Integer getOriginMenuItemsCount() {
        return listDDMenuOrigins.size();
    }

    public void selectDDMenuItem(Integer menuItemNum) {
        if (menuItemNum < listDDMenuOrigins.size()-1 && menuItemNum >= 0) {
            listDDMenuOrigins.get(menuItemNum).click();
        }
    }

    public String getSelectedOriginCity() {
//        String string1 = fMenuOrigins_1.getAttribute("value");
//        String result = string1.split(" ")[0].toLowerCase();
        return fMenuOrigins_1.getAttribute("value").split(" ")[0];
    }

    public void setOriginCity(String city) {
        fMenuOrigins_1.sendKeys(city.substring(0, 1));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        //PageFactory.initElements(this.driver, this);
    }

    public void clearOriginCity() {
        fMenuOrigins_1.clear();
    }

    /* TODO */
    public void setOriginCity(Integer position) {

    }

    public Integer getOffersNumberFromCurrentCity() {
        return listOfferOrigins.size();
    }

    public boolean isOriginsShownCorrectly() {
        boolean result = false;
//        String str;
        String selectedCity = getSelectedOriginCity().toLowerCase();
        for (int i = 0; i < listOfferOrigins.size(); i++) {
//            str = extractCityFromStr(listOfferOrigins.get(i).getText()).toLowerCase();
            if (extractCityFromStr(listOfferOrigins.get(i).getText()).toLowerCase().equals(selectedCity)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    public String extractCityFromStr(String str) {
        return  str.replaceAll("[A-Z]+?$", "");
    }

}