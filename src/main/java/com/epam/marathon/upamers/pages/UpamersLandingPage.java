package com.epam.marathon.upamers.pages;

import com.epam.marathon.upamers.pages.widgets.UpamersContactsWidget;

public class UpamersContactsWidget extends UpamersBasePage {

    @FindBy(css = "li.contact-list__item")
    private List<WebElement> contactsList;


    public List<WebElement> getContactsList() {
        return contactsList;
    }

}

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
