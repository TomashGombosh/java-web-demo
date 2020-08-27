package com.tomash.gombosh.web.tests;

import org.testng.annotations.Test;

/**
 * @author Tomash Gombosh
 */
public class LoginTest extends BaseTest {

    @Test(description = "Login by Default user")
    public void loginByDefaultUser() {
        homePage.clickSignIn();
        signInPage.fillLogin();
        signInPage.clickSignIn();
        assertThat(myAccountPage.pageIsDisplayed())
                .as("My account page should be displayed after success login")
                .isEqualTo(true);
    }
}
