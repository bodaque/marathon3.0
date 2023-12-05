package com.epam.marathon.ui;

import com.epam.marathon.UITestBase;

@Test
@Slf4j
public class LogInUITest extends UITestBase {

    private LandingPageBO landingPageBO;
    private HomePageBO homePageBO;

    @BeforeMethod
    public void pageInit() {
        landingPageBO = new LandingPageBO();
        homePageBO = new HomePageBO();
    }

    @Test(description = "Verify all elements are displayed on the Home page")
    public void homePageTest() {
        Assert.assertTrue(landingPageBO.isLogInButtonDisplayed(), "Log In button is not displayed on the Home page");
        Assert.assertTrue(landingPageBO.isHeaderLogoDisplayed(), "Header Logo is not displayed on the Home page");
        Assert.assertFalse(landingPageBO.isContactsListEmpty(), "Some contacts should be displayed on the Home page");
    }

    @Test(description = "Verify Login Banner test")
    public void loginBannerTest() {
        Assert.assertTrue(landingPageBO.isLoginBannerDisplayed(), "Banner is not displayed on the Home page");
        Assert.assertEquals(landingPageBO.getBannerHeaderText(), "Welcome everyone", "Banner header text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerContentText(), "Welcome everyone", "Banner content text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerLinkText(), "Welcome everyone", "Banner link text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerLinkReference(), "Welcome everyone", "Banner link reference is incorrect");
    }


    @Test(description = "Login Banner close test")
    public void loginBannerCloseTest() {
        Assert.assertTrue(landingPageBO.isLoginBannerDisplayed(), "Banner is not displayed on the Home page");
        landingPageBO.clickBannerCloseButton();
        Assert.assertFalse(landingPageBO.isLoginBannerDisplayed(), "Banner is displayed on the Home page");
    }

    @Test(description = "Log In test")
    public void loginTest() {
        String login = "epamtestmail3@gmail.com";
        String pwd = "Marathon3.0-auto";
        landingPageBO.logIn(login, pwd);
        Assert.assertTrue(homePageBO.isAvatarHeaderButtonDisplayed(), "Avatar header icon is not displayed");
    }
}

import lombok.extern.slf4j.Slf4j;
import com.epam.marathon.upamers.bo.HomePageBO;

@Slf4j
public class HomePageBO {

    public UpamersHomePage homePage;

    public HomePageBO() {
        homePage = new UpamersHomePage();
    }

    public boolean isAvatarHeaderButtonDisplayed() {
        log.info("Verify avatar header button is displayed");
        return homePage.getAvatarIconButton().isDisplayed();
    }
}

import com.epam.marathon.upamers.bo.LandingPageBO;

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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
@Slf4j
public class LogInUITest extends UITestBase {

    private LandingPageBO landingPageBO;
    private HomePageBO homePageBO;

    @BeforeMethod
    public void pageInit() {
        landingPageBO = new LandingPageBO();
        homePageBO = new HomePageBO();
    }

    @Test(description = "Verify all elements are displayed on the Home page")
    public void homePageTest() {
        Assert.assertTrue(landingPageBO.isLogInButtonDisplayed(), "Log In button is not displayed on the Home page");
        Assert.assertTrue(landingPageBO.isHeaderLogoDisplayed(), "Header Logo is not displayed on the Home page");
        Assert.assertFalse(landingPageBO.isContactsListEmpty(), "Some contacts should be displayed on the Home page");
    }

    @Test(description = "Verify Login Banner test")
    public void loginBannerTest() {
        Assert.assertTrue(landingPageBO.isLoginBannerDisplayed(), "Banner is not displayed on the Home page");
        Assert.assertEquals(landingPageBO.getBannerHeaderText(), "Welcome everyone", "Banner header text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerContentText(), "Welcome everyone", "Banner content text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerLinkText(), "Welcome everyone", "Banner link text is incorrect");
        Assert.assertEquals(landingPageBO.getBannerLinkReference(), "Welcome everyone", "Banner link reference is incorrect");
    }


    @Test(description = "Login Banner close test")
    public void loginBannerCloseTest() {
        Assert.assertTrue(landingPageBO.isLoginBannerDisplayed(), "Banner is not displayed on the Home page");
        landingPageBO.clickBannerCloseButton();
        Assert.assertFalse(landingPageBO.isLoginBannerDisplayed(), "Banner is displayed on the Home page");
    }

    @Test(description = "Log In test")
    public void loginTest() {
        String login = "epamtestmail3@gmail.com";
        String pwd = "Marathon3.0-auto";
        landingPageBO.logIn(login, pwd);
        Assert.assertTrue(homePageBO.isAvatarHeaderButtonDisplayed(), "Avatar header icon is not displayed");
    }
}
