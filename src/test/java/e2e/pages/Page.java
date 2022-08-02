package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
