package com.epam.marathon.upamers.pages;

import com.epam.marathon.auto.core.ui.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
