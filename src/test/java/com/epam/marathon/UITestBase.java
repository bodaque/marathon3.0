package com.epam.marathon;

import com.epam.marathon.auto.core.ui.DriverManager;

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

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;

@Slf4j
public abstract class UITestBase {

    @AfterMethod
    public void tearDown() {
        DriverManager.getInstance().tearDown();
    }
}
