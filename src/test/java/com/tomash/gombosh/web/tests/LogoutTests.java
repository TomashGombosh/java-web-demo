package com.tomash.gombosh.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.tomash.gombosh.web.utils.ApplicationProperties.BASE_URL;

/**
 * @author Tomash Gombosh
 */
public class LogoutTests extends BaseTest {
    @BeforeMethod
    private void login() {
        homePage.clickSignIn();
        signInPage.fillLogin();
        signInPage.clickSignIn();
        assertThat(myAccountPage.pageIsDisplayed())
                .as("My account page should be displayed after success login")
                .isEqualTo(true);
    }

    @Test(description = "Logout by button")
    public void logoutByButton() {
        myAccountPage.clickSignOut();
        assertThat(homePage.pageIsDisplayed())
                .as("Home page should be displayed after success logout")
                .isEqualTo(true);
    }

    @Test(description = "Logout by url")
    public void logoutByUrl() {
        DRIVER.get(String.format("%s?mylogout=", BASE_URL));
        assertThat(homePage.pageIsDisplayed())
                .as("Home page should be displayed after success logout")
                .isEqualTo(true);
    }
}
