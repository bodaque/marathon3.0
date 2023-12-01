package com.epam.marathon.upamers.bo;

import com.epam.marathon.upamers.pages.UpamersLandingPage;
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
