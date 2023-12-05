package com.epam.marathon.auto.core.ui;

import com.epam.marathon.auto.core.BASE_URI;

public enum BASE_URI {
    UI(""),
    API("");

    private String uri;
    BASE_URI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DriverManager {

    private DriverManager() {
    }

    private static final DriverManager instance = new DriverManager();

    public static DriverManager getInstance() {
        return instance;
    }

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driverPool.get() == null) {
            driverPool.set(initWebDriver());
        }
        return driverPool.get();
    }

    static WebDriver initWebDriver() {
        String chromedriverPath = "src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-position=300,-900");

        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get(BASE_URI.UI.getUri());
        webDriver.manage().timeouts().implicitlyWait(Duration.of(20, ChronoUnit.SECONDS));

        return webDriver;
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driverPool.remove();
    }
}
