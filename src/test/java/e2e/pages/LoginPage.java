package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends Page{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "login_password")
    private WebElement passwordCredentials;

    @FindBy(how = How.CSS, using = "input[name='user-name']")
    private WebElement usernameField;

    @FindBy(how = How.CSS, using = "input[name='password']")
    private WebElement passwordField;

    @FindBy(how = How.CSS, using = "input[name='login-button']")
    private WebElement loginButton;


    public void logOnPage(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public WebElement getPasswordCredentials() {
        return passwordCredentials;
    }
}
