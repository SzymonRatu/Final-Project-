package zadanka.page;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;


public class OrderConfirmationPage {

    private final WebDriver driver;

    @FindBy(css = "a[title='View my customer account']")
    private WebElement yourAccountBtn;

    @FindBy(xpath = "//*[@id=\"order-items\"]/div[2]/table/tbody/tr[4]/td[2]")
    private WebElement orderPrice;


    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void screenShot() throws IOException {
        // Take a screenshot and store it as a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Add to name date to the identification
        DateTimeFormatter format = DateTimeFormatter.ofPattern("-HH-mm-ss__dd-MM-yyyy");
        String date = (LocalDateTime.now().format(format));

        // Specify the location and name to save the screenshot
        String Path = "screenshots/screenshot " + date + ".png";

        // Copy the file to the specified location
        FileUtils.copyFile(screenshot, new File(Path));
    }

    public String getOrderPrice(){
        return orderPrice.getText();
    }

    public YourAccountPage clickYourAccountBtn(){
        yourAccountBtn.click();
        return new YourAccountPage(driver);
    }
}
