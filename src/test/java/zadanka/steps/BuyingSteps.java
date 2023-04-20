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

import java.io.IOException;
import java.time.Duration;

public class BuyingSteps {

    private WebDriver driver;
    private String totalPrice;

    @Given("Logged user with email {string} and password {string} to shop")
    public void loginToShop(String email, String passwd) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl/index.php");

        // Open new brose and open a shop main page
        // Login with email and password to existing account

        MainPage mainPage = new MainPage(driver);
        AuthenticationPage authenticationPage = mainPage.signIn();
        YourAccountPage yourAccountPage = authenticationPage.logIn(email, passwd);
        yourAccountPage.goToMainPage();
    }

    @When("User add {string} with discount {string}")
    public void userAddItemWithDiscount(String item, String discount) {

        // Choosing a given item
        // And checking getting discount

        MainPage mainPage = new MainPage(driver);
        ItemPage itemPage = mainPage.clickItem(item);

        Assertions.assertEquals(discount, itemPage.getDiscount());
    }

    @And("choose {string} size and choose {int} pieces of sweater")
    public void chooseSizeAndChoosePiecesOfSweater(String size, int pieces) {
        ItemPage itemPage = new ItemPage(driver);

        // Set size and quantity

        itemPage.setSize(size);
        itemPage.setQuantity(pieces);
    }

    @And("add to cart with size {string} and {int} pieces")
    public void addToCart(String size, int pieces) {

        // Add item to cart

        ItemPage itemPage = new ItemPage(driver);
        itemPage.clickAddToCart();

        // And checking added item size and quantity

        itemPage.waitForModal();
        Assertions.assertEquals(size, itemPage.getModalSize());
        Assertions.assertEquals(Integer.toString(pieces),itemPage.getModalQuantity());

        ShoppingCartPage shoppingCartPage = itemPage.clickProceedToCheckOut();
        shoppingCartPage.clickProceedToCheckoutBtn();
    }

    @And("Confirm address with alias {string}")
    public void confirmAddress(String address) {

        // Checking delivery address and continue ordering

        ControllerOrderPage controllerOrderPage = new ControllerOrderPage(driver);
        Assertions.assertEquals(controllerOrderPage.getDeliveryAddress(), address);
        controllerOrderPage.clickAddressContinue();
    }

    @And("Choose a payment option - Pay By Check")
    public void chooseAPaymentOptionPayByCheck() {

        // Choosing payment as Pay by check and place order

        ControllerOrderPage controllerOrderPage = new ControllerOrderPage(driver);
        controllerOrderPage.clickPayByCheck();
        controllerOrderPage.clickTermsAndConditions();
        controllerOrderPage.clickPlaceOrderBtn();
    }

    @Then("Take a print-screen with confirm order")
    public void takeAPrintScreenWithConfirmOrder() throws IOException {

        // Take a screenshot confirmation order

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.screenShot();

        totalPrice = orderConfirmationPage.getOrderPrice();
    }

    @And("In history order check order with status {string}")
    public void inHistoryOrderCheckOrderWithStatus(String text) {
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        YourAccountPage yourAccountPage = orderConfirmationPage.clickYourAccountBtn();
        OrderHistoryPage orderHistoryPage = yourAccountPage.clickOrderHistoryAndDetails();

        Assertions.assertEquals(text, orderHistoryPage.getLatestOrderStatus());
    }

    @And("check total price")
    public void checkTotalPrice() {
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        Assertions.assertEquals(totalPrice, orderHistoryPage.getLatestOrderPrice());
    }

    @And("close browser")
    public void closeBrowser() {
        driver.quit();
    }

    @And("Choose a pick up in store")
    public void chooseAPickUpInStore() {
        ControllerOrderPage controllerOrderPage = new ControllerOrderPage(driver);
        controllerOrderPage.clickDeliveryContinue();
    }
}
