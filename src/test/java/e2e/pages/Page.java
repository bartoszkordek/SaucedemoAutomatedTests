package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Page {

    public Page(){}

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public class Error {

        public Error(WebDriver driver){
            PageFactory.initElements(driver, this);
        }

        @FindBy(how = How.CSS, using = "div[class='error-message-container error']")
        List<WebElement> messageContainers;

        public List<WebElement> getMessageContainers() {
            return messageContainers;
        }

        @FindBy(how = How.CSS, using = "div[class='error-message-container error']")
        WebElement messageContainer;

        public WebElement getMessageContainer() {
            return messageContainer;
        }

        @FindBy(how = How.XPATH, using = "//h3[@data-test='error']")
        WebElement header;

        public WebElement getHeader() {
            return header;
        }

        @FindBy(how = How.CLASS_NAME, using = "error-button")
        WebElement button;

        public WebElement getButton() {
            return button;
        }
    }


}
