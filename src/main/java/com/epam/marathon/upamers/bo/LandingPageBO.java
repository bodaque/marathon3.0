package com.epam.marathon.upamers.bo;

import com.epam.marathon.upamers.pages.UpamersLandingPage;

public class UpamersLandingPage extends UpamersBasePage {

    private final UpamersContactsWidget upamersContactsWidget = new UpamersContactsWidget();
    private final LogInBannerWidget logInBannerWidget = new LogInBannerWidget();

    private final LogInWidget logInWidget = new LogInWidget();

    @FindBy(css = "epm-header img.logo")
    private WebElement headerIcon;
    @FindBy(css = "a[href='#/auth/log-in']")
    private WebElement logInButton;

    public WebElement getHeaderIcon() {
        return headerIcon;
    }

    public WebElement getLogInButton() {
        return logInButton;
    }

    public UpamersContactsWidget getUpamersContactsWidget() {
        return upamersContactsWidget;
    }

    public LogInBannerWidget getLogInBannerWidget() {
        return logInBannerWidget;
    }

    public LogInWidget getLogInWidget() {
        return logInWidget;
    }

    public static class LogInBannerWidget extends UpamersBasePage {

        @FindBy(css = "header epm-toaster")
        private WebElement bannerContainer;

        public WebElement getBannerContainer() {
            return bannerContainer;
        }

        public WebElement getBannerHeader() {
            return getBannerContainer().findElement(By.cssSelector(".toaster__title"));
        }

        public WebElement getBannerContent() {
            return getBannerContainer().findElement(By.cssSelector(".toaster__description"));
        }

        public WebElement getBannerLink() {
            return getBannerContent().findElement(By.cssSelector("a.toaster__toaster-link"));
        }

        public WebElement getBannerXButton() {
            return getBannerContainer().findElement(By.cssSelector(".toaster__close-btn"));
        }
    }


    public static class LogInWidget extends UpamersBasePage {

        @FindBy(css = "header")
        private WebElement loginHeader;
        @FindBy(css = "epm-input[labelplaceholder=\"Login\"] input")
        private WebElement loginInput;
        @FindBy(css = "epm-input[labelplaceholder=\"Password\"] input")
        private WebElement pwdInput;
        @FindBy(css = "epm-log-in epm-button.submit-button")
        private WebElement loginButton;

        public WebElement getLoginHeader() {
            return loginHeader;
        }

        public WebElement getLoginInput() {
            return loginInput;
        }

        public WebElement getPwdInput() {
            return pwdInput;
        }

        public WebElement getLoginButton() {
            return loginButton;
        }
    }
}

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class LandingPageBO {

    public UpamersLandingPage landingPage;

    public LandingPageBO() {
        landingPage = new UpamersLandingPage();
    }

    public boolean isLogInButtonDisplayed() {
        log.info("Verify 'Log in' button is displayed");
        return landingPage.getLogInButton().isDisplayed();
    }

    public boolean isHeaderLogoDisplayed() {
        log.info("Verify header logo is displayed");
        return landingPage.getHeaderIcon().isDisplayed();
    }

    public boolean isLoginBannerDisplayed() {
        log.info("Verify login banner is displayed");
        return landingPage.getLogInBannerWidget().getBannerContainer().isDisplayed();
    }

    public boolean isContactsListEmpty() {
        log.info("Verify if contacts list is empty");
        return landingPage.getUpamersContactsWidget().getContactsList().isEmpty();
    }

    public void clickLogInButton() {
        log.info("Click 'Log In' button");
        landingPage.getLogInButton().click();
    }

    public String getBannerHeaderText() {
        log.info("Get banner header text");
        return landingPage.getLogInBannerWidget().getBannerHeader().getText();
    }

    public String getBannerContentText() {
        log.info("Get banner header text");
        return landingPage.getLogInBannerWidget().getBannerContent().getText();
    }

    public String getBannerLinkText() {
        log.info("Get banner link text");
        return landingPage.getLogInBannerWidget().getBannerLink().getText();
    }

    public String getBannerLinkReference() {
        log.info("Get banner link reference");
        return landingPage.getLogInBannerWidget().getBannerLink().getAttribute("href");
    }

    public void clickBannerCloseButton() {
        log.info("Click banner X button");
        landingPage.getLogInBannerWidget().getBannerXButton().click();

        log.info("We expect it will take another second to take effect");
        WebDriverWait wait = new WebDriverWait(landingPage.getDriver(), Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.invisibilityOf(landingPage.getLogInBannerWidget().getBannerContainer()));
    }

    public void logIn(String userName, String pwd) {
        clickLogInButton();
        enterLoginCredentials(userName, pwd);
        submitLoginCredentials();
    }

    private void submitLoginCredentials() {
        log.info("Submit log in credentials");
        landingPage.getLogInWidget().getLoginButton().click();
    }

    private void enterLoginCredentials(String userName, String pwd) {
        log.info("Enter login credentials: %s %s".formatted(userName, pwd));
        landingPage.getLogInWidget().getLoginInput().sendKeys(userName);
        landingPage.getLogInWidget().getPwdInput().sendKeys(pwd);
    }

    public boolean isContactDisplayed(String name) {
        log.info("Get contact: " + name);
        landingPage.getUpamersContactsWidget().getContactsList();

        return true;
    }
}
