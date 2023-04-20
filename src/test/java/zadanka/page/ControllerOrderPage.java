package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ControllerOrderPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"delivery-addresses\"]/article/header/label/span[2]")
    private WebElement deliveryAddressAlias;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckCheckbox;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//div[@id=\"payment-confirmation\"]/div/button")
    private WebElement placeOrderBtn;

    @FindBy(name = "confirm-addresses")
    private WebElement addressContinueBtn;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement deliveryContinueBtn;



    public ControllerOrderPage(WebDriver driver) {
         this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDeliveryAddress(){
        return deliveryAddressAlias.getText();
    }

    public void clickAddressContinue(){
        addressContinueBtn.click();
    }


    public void clickPayByCheck(){
        if(!payByCheckCheckbox.isSelected()) {
            payByCheckCheckbox.click();
        }
    }

    public void clickTermsAndConditions(){
        if(!termsAndConditionsCheckbox.isSelected()){
            termsAndConditionsCheckbox.click();
        }
    }

    public void clickDeliveryContinue(){
        deliveryContinueBtn.click();
    }

    public void clickPlaceOrderBtn(){
        placeOrderBtn.click();
    }

}
