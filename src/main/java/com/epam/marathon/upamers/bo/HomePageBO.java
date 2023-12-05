package com.epam.marathon.upamers.bo;

import com.epam.marathon.upamers.pages.UpamersHomePage;

public class UpamersHomePage extends UpamersBasePage {

    @FindBy(css = "epm-header button.avatar-wrapper")
    private WebElement avatarIconButton;

    public WebElement getAvatarIconButton() {
        return avatarIconButton;
    }
}

import lombok.extern.slf4j.Slf4j;

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
