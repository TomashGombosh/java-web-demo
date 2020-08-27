package com.tomash.gombosh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tomash.gombosh.web.services.AbstractServicePage;
import com.tomash.gombosh.web.services.PageElement;

/**
 * @author Tomash Gombosh
 */
public class HomePage extends AbstractServicePage {
    private static final PageElement TITLE = new PageElement(
            "Title of the Home page",
            By.cssSelector(".nav+div>div.container"),
            true);
    private static final PageElement SIGN_IN_BUTTON = new PageElement(
            "Login button on the Home page",
            By.cssSelector(".login"),
            true);

    public HomePage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed() {
        waitToBeVisible(TITLE);
        return allRequiredElementDisplayed();
    }

    public void clickSignIn() {
        waitToBeClickable(SIGN_IN_BUTTON);
        click(SIGN_IN_BUTTON);
    }
}
