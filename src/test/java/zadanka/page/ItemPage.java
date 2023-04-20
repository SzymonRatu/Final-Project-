package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage {

    private final WebDriver driver;


    @FindBy(css= "li.product-flag.discount")
    private WebElement productDiscount;

    @FindBy(id = "group_1")
    private WebElement sizeSelector;

    @FindBy(xpath = "//*[@id='blockcart-modal']/div/div/div[2]/div/div[1]/div/div[2]/span[1]/strong")
    private WebElement modalSize;

    @FindBy(xpath = "//*[@id='blockcart-modal']/div/div/div[2]/div/div[1]/div/div[2]/span[2]/strong")
    private WebElement modalQuantity;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private WebElement quantityUpBtn;

    @FindBy(css = "button.add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = "div.cart-content-btn>a")
    private WebElement proceedToCheckOutBtn;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDiscount(){
        return productDiscount.getText();
    }

    public void setSize (String Size){
        Select select = new Select(sizeSelector);
        select.selectByVisibleText(Size);
    }

    public void setQuantity(int amount) {
        for (int i = 0; i < amount-1; i++) {
            quantityUpBtn.click();
        }
    }

    public void clickAddToCart(){
        addToCartBtn.click();
    }

    public String getModalSize(){
        return modalSize.getText();
    }

    public String getModalQuantity(){
        return modalQuantity.getText();
    }

    public ShoppingCartPage clickProceedToCheckOut(){
        proceedToCheckOutBtn.click();
        return new ShoppingCartPage(driver);
    }

    public void waitForModal(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(modalSize));
    }


}
