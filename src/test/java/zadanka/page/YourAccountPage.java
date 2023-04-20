package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAccountPage {

    private final WebDriver driver;

    @FindBy(css = "a[title='Addresses']")
    private WebElement addressesBtn;

    @FindBy(css = "div[id=\"_desktop_logo\"] > a")
    private WebElement mainLogo;

    @FindBy (id = "history-link")
    private WebElement orderHistoryAndDetails;

    public YourAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YourAddressesPage clickAddresses(){
        addressesBtn.click();
        return new YourAddressesPage(driver);
    }

    public MainPage goToMainPage(){
        mainLogo.click();
        return new MainPage(driver);
    }

    public OrderHistoryPage clickOrderHistoryAndDetails(){
        orderHistoryAndDetails.click();
        return new OrderHistoryPage(driver);
    }


}
