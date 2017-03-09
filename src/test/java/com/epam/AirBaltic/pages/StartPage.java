package com.epam.AirBaltic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


/**
 * Sample page
 */
public class StartPage extends Page {


    @FindBy(how = How.TAG_NAME, using = "h1")
    public WebElement header;

    @FindBy(xpath = "//button[@id='flights-form-btn']")
    WebElement btnBookAndFlights;

    @FindBy(xpath = "//input[@id='flt_destin_text']")
    WebElement inptDestTo;

    @FindBy(id = "myairbaltic-href")
    private WebElement buttonMyAirBaltic;

    @FindBy(css = "#dropdownMenu3")
    private WebElement buttonMyAccount;

    @FindBy(xpath = "//li[@role='presentation']/a[@href='#']")
    private WebElement buttonLogout;

    @FindBy(xpath = "//li[@role='presentation']/a[@href='/edit-a-customer-account']")
    private WebElement buttonEditMyProfile;

    @FindBy(xpath = "//li[2]/a[@id='ancillary-2-en']/span[@class='product-description']/div/strong")
    public WebElement linkFlySnowyPeaks;  //testcase #6

    @FindBy(css = "div.menu-item.drop-flights")
    public WebElement linkFlights;  //testcase #4

    //ul/li/a[@href='/youth-offer']
    @FindBy(xpath = "//div[@class='mega-list']/ul/li/a[@href='/youth-offer']")
    public WebElement linkYouthOffer;  //testcase #4

    @FindBy(xpath = "//div[@class='footer-menu col-md-7 col-lg-7']/a[3]")
    public WebElement linkCareer;

    private static final By LINK_LOGIN_FORM = By.id("myairbaltic-href");
    private static final By LINK_DD_USERMENU = By.cssSelector("a#dropdownMenu3.dropdown-toggle");
    private static final By POPUP_NOTHANKS_BUTTON = By.cssSelector("div.insider-opt-in-notification-button.insider-opt-in-disallow-button");

    public StartPage(WebDriver driver) {
        super(driver);
    }


    public StartPage loginCheck() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonMyAccount));
        return new StartPage(driver);
    }

    public BookAFlightForm goToBookAFlightForm() {
        return new BookAFlightForm(driver);
    }

    public LoginForm goToLoginForm(){
        buttonMyAirBaltic.click();
        return new LoginForm(driver);
    }

    public void logout(String url){
        driver.navigate().to(url);
        buttonMyAccount.click();
        buttonLogout.click();
        driver.manage().deleteAllCookies();
    }

    public EditProfilePage goToEditProfilePage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonMyAccount));
        buttonMyAccount.click();
        buttonEditMyProfile.click();
        return new EditProfilePage(driver);
    }

    public SnowyPeaksPage clickFlySnowyPeaksLink() {
        clickOnElementWithJS(linkFlySnowyPeaks);
//        linkFlySnowyPeaks.click();
//        (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.
//                titleContains("Winter"));
        System.out.println("Opened page with title " + driver.getTitle());
        return new SnowyPeaksPage(this.driver);

    }

    public void clickFlightsLink() {
        linkFlights.click();
    }

    public void clickYouthOfferLink() {
        linkYouthOffer.click();
    }

    public YouthOfferPage gotoYouthOfferPageByLink() {
        this.clickFlightsLink();
        (new WebDriverWait(this.driver, 5)).until(ExpectedConditions.
                visibilityOf(linkYouthOffer));
        this.clickYouthOfferLink();
        return new YouthOfferPage(this.driver);
    }

    public void clickCareerLink() {
        linkCareer.click();
        (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.
                titleContains("Career"));
        System.out.println(driver.getTitle());
    }

    public void clickOnElementWithJS(WebElement element) {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);"
                ;
        ((JavascriptExecutor)driver).executeScript(script, element);
    }

}
