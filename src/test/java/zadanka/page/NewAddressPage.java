package zadanka.page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class NewAddressPage {

    private final WebDriver driver;

    @FindBy(id = "field-alias")
    private WebElement aliasInput;

    @FindBy(id = "field-address1")
    private WebElement addressInput;

    @FindBy(id = "field-city")
    private WebElement cityInput;

    @FindBy(id = "field-id_state")
    private WebElement stateSelector;

    @FindBy(id = "field-postcode")
    private WebElement postcodeInput;

    @FindBy(id = "field-id_country")
    private WebElement countrySelector;

    @FindBy(id = "field-phone")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/footer/button")
    private WebElement saveBtn;

    @FindBy(css = "a.account-link")
    private WebElement backToYourAccountBtn;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterDataAndSave(String alias, String address, String city, String zipcode, String country, String phone) {
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        postcodeInput.clear();
        postcodeInput.sendKeys(zipcode);

        // Select visible options with given country

        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByVisibleText(country);

        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void saveBtn(){
        saveBtn.click();
    }

    public String getAlias() {
        return aliasInput.getAttribute("value");
    }

    public String getAddress() {
        return addressInput.getAttribute("value");
    }

    public String getCity() {
        return cityInput.getAttribute("value");
    }

    public String getPostcode() {
        return postcodeInput.getAttribute("value");
    }

    public String getCountry() {

        // Returning a text first selected option

        Select select = new Select(countrySelector);
        return select.getFirstSelectedOption().getText();
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    public void backToYourAccount() {
        backToYourAccountBtn.click();
    }

}
