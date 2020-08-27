package com.tomash.gombosh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tomash.gombosh.web.services.AbstractServicePage;
import com.tomash.gombosh.web.services.PageElement;

/**
 * @author Tomash Gombosh
 */
public class MyAccountPage extends AbstractServicePage {
    private static final PageElement MY_ACCOUNT_LAYOUT = new PageElement(
            "My account layout",
            By.id("center_column"),
            true);
    private static final PageElement SIGN_OUT_BUTTON = new PageElement(
            "Sign out button",
            By.cssSelector(".logout"),
            true);

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed() {
        waitToBeVisible(MY_ACCOUNT_LAYOUT);
        return allRequiredElementDisplayed();
    }

    public void clickSignOut() {
        click(SIGN_OUT_BUTTON);
    }
}
