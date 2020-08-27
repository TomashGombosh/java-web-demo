package com.tomash.gombosh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tomash.gombosh.web.models.user.User;
import com.tomash.gombosh.web.models.user.UserFactory;
import com.tomash.gombosh.web.services.AbstractServicePage;
import com.tomash.gombosh.web.services.PageElement;

/**
 * @author Tomash Gombosh
 */
public class SignInPage extends AbstractServicePage {
    private static final PageElement EMAIL_FIELD = new PageElement(
            "Email input field",
            By.id("email"),
            true);
    private static final PageElement PASSWORD_FIELD = new PageElement(
            "Password input field",
            By.id("passwd"),
            true);
    private static final PageElement SUBMIT_BUTTON = new PageElement(
            "Submit login button",
            By.id("SubmitLogin"),
            true);

    public SignInPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed() {
        waitToBeVisible(EMAIL_FIELD);
        return allRequiredElementDisplayed();
    }

    public void fillLogin(final String email, final String password) {
        waitToBeVisible(EMAIL_FIELD);
        enterText(EMAIL_FIELD, email);
        enterText(PASSWORD_FIELD, password);
    }

    public void fillLogin(final User user) {
        this.fillLogin(user.getEmail(), user.getPassword());
    }

    public void fillLogin() {
        this.fillLogin(new UserFactory().create());
    }

    public void clickSignIn() {
        click(SUBMIT_BUTTON);
    }
}
