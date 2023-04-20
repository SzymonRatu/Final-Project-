package zadanka.page;

import com.beust.ah.A;
import io.cucumber.java.bs.I;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private  final WebDriver driver;

    @FindBy(css = "a[title='Log in to your customer account']")
    private WebElement sigInBtn;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage signIn() {
        sigInBtn.click();
        return new AuthenticationPage(driver);
    }

    public ItemPage clickItem(String item){
        String xpath = "//a[text()='" + item + "']" ;
        driver.findElement(By.xpath(xpath)).click();
        return new ItemPage(driver);
    }


}
