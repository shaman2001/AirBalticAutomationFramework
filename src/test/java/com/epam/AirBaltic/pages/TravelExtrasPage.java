package com.epam.AirBaltic.pages;

import com.epam.AirBaltic.util.AdditionalConditions;
import com.epam.AirBaltic.util.FluentWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Katerina_Karpenia on 3/2/2017.
 */
public class TravelExtrasPage extends Page {

    private static final String EURO_SPLITTERS = "[€ ]";
    private By totalPriceContainer = By.xpath("//div[@class='fare-bottom']//span[2]");

    @FindBy(xpath = "//*[contains(text(), 'Seat me anywhere')]")
    private WebElement seatMeAnyWhereButton;

    @FindBy(css = "div>#btn-continue-booking")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@class='seats-map']//table[contains(@class,'map-plane')]//td/div[contains(@class,'seat-available')]")
    private List<WebElement> availableSeats;


    public TravelExtrasPage(WebDriver driver) {
        super(driver);
    }

    public SummaryPage goToSummaryPage() {
        seatMeAnyWhereButton.click();
        continueButton.click();
        return new SummaryPage(driver);
    }

    public boolean isOneSeatSelected() {
        (new WebDriverWait(this.driver, WAIT_5_SEC)).until(AdditionalConditions.
                jQueryCompleted());
        availableSeats.get(0).click();
        availableSeats.get(1).click();
        return availableSeats.get(0).isSelected();
    }

    private double parseTotalPrice() {
        String totalPriceString = driver.findElement(totalPriceContainer).getText().replaceAll(EURO_SPLITTERS, "");
        return Double.parseDouble(totalPriceString);
    }

    public boolean checkSummaryPrice() {
        parseTotalPrice();
        double departmentPrice = FlightsAndTicketTypesPage.getDeparturePrice();
        double returnPrice = FlightsAndTicketTypesPage.getReturnPrice();
        boolean result = (parseTotalPrice() == departmentPrice + returnPrice);
        return result;
    }

}
