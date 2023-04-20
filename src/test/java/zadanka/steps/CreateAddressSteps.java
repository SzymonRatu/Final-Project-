package zadanka.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import zadanka.page.*;

import java.time.Duration;

public class CreateAddressSteps {

    private WebDriver driver;
    int numberOfAddress;

    @Given("Logged user with email {string} and password {string}")
    public void loginToShop(String email, String passwd) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl/index.php");

        // Open new brose and open a shop main page
        // Login with email and password to existing account

        MainPage mainPage = new MainPage(driver);
        AuthenticationPage authenticationPage = mainPage.signIn();
        YourAccountPage yourAccountPage = authenticationPage.logIn(email, passwd);
        yourAccountPage.clickAddresses();
    }

    @When("User is on my address page and User add new address")
    public void userIsOnMyAddressPage() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        numberOfAddress = yourAddressesPage.getAddressSize();
        yourAddressesPage.createNewAddress();
    }

    @And("User enter new address {string}, {string}, {string}, {string}, {string}, {string}")
    public void userEnterNewAddress(String alias, String address, String city, String zipCode, String country,
                                    String phone){

        NewAddressPage newAddressPage = new NewAddressPage(driver);
        newAddressPage.enterDataAndSave(alias, address, city, zipCode, country, phone);
        newAddressPage.saveBtn();
    }

    @Then("User sees a added address")
    public void userSeesAAddedAddress() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        Assertions.assertEquals(yourAddressesPage.getSuccessfullyAddedAlert(), "Address successfully added!");
        Assertions.assertEquals(numberOfAddress+1 , yourAddressesPage.getAddressSize());
        numberOfAddress = yourAddressesPage.getAddressSize();
    }

    @And("User verify created address {string}, {string}, {string}, {string}, {string}, {string}")
    public void userVerifyCreatedAddress(String alias, String address, String city, String zipCode,
                                         String country, String phone) {

        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        NewAddressPage newAddressPage = yourAddressesPage.clickUpdateAddress();

        Assertions.assertEquals(newAddressPage.getAlias(), alias);
        Assertions.assertEquals(newAddressPage.getAddress(), address);
        Assertions.assertEquals(newAddressPage.getCity(), city);
        Assertions.assertEquals(newAddressPage.getPostcode(), zipCode);
        Assertions.assertEquals(newAddressPage.getPhone(), phone);
        Assertions.assertEquals(newAddressPage.getCountry(), country);

        newAddressPage.backToYourAccount();
    }

    @And("User remove the address")
    public void userRemoveTheAddress() {
        YourAccountPage yourAccountPage = new YourAccountPage(driver);
        YourAddressesPage yourAddressesPage = yourAccountPage.clickAddresses();
        yourAddressesPage.deleteAddress();
    }

    @And("User sees the address was removed")
    public void userSeesTheAddressWasRemoved() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        Assertions.assertEquals(yourAddressesPage.getSuccessfullyAddedAlert(), "Address successfully deleted!");
        Assertions.assertEquals(numberOfAddress-1 , yourAddressesPage.getAddressSize());
    }

    @And("User close the browser")
    public void userCloseTheBrowser() {
        driver.quit();
    }

}
