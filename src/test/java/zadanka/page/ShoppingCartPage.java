package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    private final WebDriver driver;

    @FindBy(css = "div.text-sm-center > a")
    private WebElement proceedToCheckoutBtn;



    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProceedToCheckoutBtn(){
        proceedToCheckoutBtn.click();
    }




}
