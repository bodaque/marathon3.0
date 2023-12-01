package com.epam.marathon.upamers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpamersHomePage extends UpamersBasePage {

    @FindBy(css = "epm-header button.avatar-wrapper")
    private WebElement avatarIconButton;

    public WebElement getAvatarIconButton() {
        return avatarIconButton;
    }
}
