package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourAddressesPage {

    private final WebDriver driver;

    @FindBy(css = "div.addresses-footer > a")
    private WebElement createNewAccountBtn;

    @FindBy(css= "article.alert > ul > li ")
    private WebElement successfullyAddedAlert;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/article/div[2]/a[1]")
    private WebElement updateAddressBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/article/div[2]/a[2]")
    private WebElement deleteAddressBtn;

    @FindBy(css = "article.address")
    private List<WebElement> addressElements;



    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewAddress()
    {
        createNewAccountBtn.click();
    }

    public String getSuccessfullyAddedAlert() {
        return successfullyAddedAlert.getText();
    }

    public NewAddressPage clickUpdateAddress(){
        updateAddressBtn.click();
        return new NewAddressPage(driver);
    }

    public void deleteAddress(){
        deleteAddressBtn.click();
    }

    public int getAddressSize(){
        return addressElements.size();
    }

}
