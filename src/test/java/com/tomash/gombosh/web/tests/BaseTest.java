package com.tomash.gombosh.web.tests;

import java.util.concurrent.TimeUnit;

import com.tomash.gombosh.web.config.driver.Browsers;
import com.tomash.gombosh.web.config.driver.DriverFactory;
import com.tomash.gombosh.web.pages.HomePage;
import com.tomash.gombosh.web.pages.MyAccountPage;
import com.tomash.gombosh.web.pages.SignInPage;
import org.assertj.core.api.WithAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.tomash.gombosh.web.utils.ApplicationProperties.BASE_URL;
import static com.tomash.gombosh.web.utils.ApplicationProperties.BROWSER;

/**
 * @author Tomash Gombosh
 */
public class BaseTest implements WithAssertions {
    public static WebDriver DRIVER;
    protected HomePage homePage;
    protected MyAccountPage myAccountPage;
    protected SignInPage signInPage;

    @BeforeMethod
    public void setupTestRun() {
        final String browser = BROWSER.toUpperCase();
        DRIVER = new DriverFactory().setBrowsers(Browsers.valueOf(browser)).create();
        DRIVER.manage().window().maximize();
        DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        initPages();
        DRIVER.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        DRIVER.quit();
    }

    private void initPages() {
        homePage = new HomePage(DRIVER);
        myAccountPage = new MyAccountPage(DRIVER);
        signInPage = new SignInPage(DRIVER);
    }
}
