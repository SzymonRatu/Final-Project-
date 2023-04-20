package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[4]/span")
    private WebElement latestOrderStatus;

    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[2]")
    private WebElement latestOrderPrice;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getLatestOrderStatus(){
        return latestOrderStatus.getText();
    }

    public String getLatestOrderPrice(){
        return latestOrderPrice.getText();
    }
}
