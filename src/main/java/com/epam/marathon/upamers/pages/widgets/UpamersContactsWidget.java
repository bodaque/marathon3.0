package com.epam.marathon.upamers.pages.widgets;

import com.epam.marathon.upamers.pages.UpamersBasePage;

public abstract class UpamersBasePage {

    private final WebDriver driver;

    public UpamersBasePage() {
        driver = DriverManager.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UpamersContactsWidget extends UpamersBasePage {

    @FindBy(css = "li.contact-list__item")
    private List<WebElement> contactsList;


    public List<WebElement> getContactsList() {
        return contactsList;
    }

}
