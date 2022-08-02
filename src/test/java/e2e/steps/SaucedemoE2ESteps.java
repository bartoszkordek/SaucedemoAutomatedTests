package e2e.steps;

import e2e.pages.LoginPage;
import e2e.pages.Page;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SaucedemoE2ESteps {

    private static final Logger logger = LoggerFactory.getLogger(SaucedemoE2ESteps.class);

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        maximizeWindow();
        setUpPageLoadTimeout(7);
        setUpImplicitTimeout(1);
        setUpDefaultExplicitTimeout(5);
        deleteAllCookies();
    }

    @Given("Navigate to {string}")
    public void navigate_to(String url) {
        driver.navigate().to(url);
        waitTillLoginFormIsLoaded();
    }

    @When("Log in as a {string}")
    public void log_in_as_a(String username) {
        LoginPage loginPage = new LoginPage(driver);
        WebElement passwordCredentials = loginPage.getPasswordCredentials();
        String passwordCredentialsText = passwordCredentials.getText();
        String password = passwordCredentialsText.substring(passwordCredentialsText.lastIndexOf("\n")+1).trim();
        loginPage.logOnPage(username, password);
    }

    @Then("Login is successful")
    public void login_is_successful() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        Assertions.assertFalse(isErrorMessageContainerLocated());
    }


    @Then("Login is failed")
    public void login_is_failed() {
        Page.Error errorPage = new Page().new Error(driver);
        Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        Assertions.assertTrue(isErrorMessageContainerLocated());
        Assertions.assertTrue(errorPage.getMessageContainer().isDisplayed());
        Assertions.assertEquals(
                "Epic sadface: Sorry, this user has been locked out.",
                errorPage.getHeader().getText()
        );
        Assertions.assertTrue(errorPage.getButton().isDisplayed());
    }

    @After
    public void cleanUp(){
        driver.close();
    }


    private void maximizeWindow(){
        driver.manage().window().maximize();
    }

    private void setUpPageLoadTimeout(int timeout) {
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
    }

    private void setUpImplicitTimeout(int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private void setUpDefaultExplicitTimeout(int timeout) {
        wait = new WebDriverWait(driver, timeout);
    }

    private void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    private void waitTillLoginFormIsLoaded() {
        By loginForm = By.className("login-box");
        wait.until(ExpectedConditions.presenceOfElementLocated(loginForm));
    }

    private boolean isErrorMessageContainerLocated(){
        return !new Page()
                .new Error(driver)
                .getMessageContainers()
                .isEmpty();
    }
}
