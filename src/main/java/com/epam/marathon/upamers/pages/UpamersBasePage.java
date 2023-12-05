package com.epam.marathon.upamers.pages;

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
