package zadanka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {

    private final WebDriver driver;

    @FindBy(id ="field-email")
    private WebElement emailInput;

    @FindBy(id = "field-password")
    private WebElement passwordInput;

    @FindBy(id ="submit-login")
    private WebElement signInBtn;


    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YourAccountPage logIn(String email, String passwd)
    {
        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(passwd);

        signInBtn.click();
        return new YourAccountPage(driver);
    }

}
