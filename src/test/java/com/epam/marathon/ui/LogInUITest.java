package com.epam.marathon.ui;

import com.epam.marathon.UITestBase;
import lombok.extern.slf4j.Slf4j;
import com.epam.marathon.upamers.bo.HomePageBO;
import com.epam.marathon.upamers.bo.LandingPageBO;
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
