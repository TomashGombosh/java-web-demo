package com.tomash.gombosh.web.config.driver;

import java.util.HashMap;
import java.util.Map;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import com.tomash.gombosh.web.exception.UnsupportedBrowserException;
import com.tomash.gombosh.web.services.Factory;

/**
 * @author Tomash Gombosh
 */
@Setter
@Accessors(chain = true)
public class CapabilityFactory implements Factory<DesiredCapabilities, CapabilityFactory> {
    private Browsers browsers = Browsers.CHROME;
    private boolean headless = false;

    @Override
    public DesiredCapabilities create() {
        switch (browsers) {
            case CHROME:
                return getChromeCapabilities();
            case FIREFOX:
                return getFirefoxCapabilities();
            case SAFARI:
                return getSafariCapabilities();
            case IE:
                return getInternetExplorerCapabilities();
            default:
                throw new UnsupportedBrowserException("Sorry, but that framework do not support browser", browsers.name());
        }
    }

    private DesiredCapabilities getChromeCapabilities() {
       final DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeOptions chOptions = new ChromeOptions();
        Map<String, Object> chromePreferences = new HashMap<String, Object>();

        chromePreferences.put("credentials_enable_service", false);
        chOptions.setExperimentalOption("prefs", chromePreferences);
        chOptions.addArguments("--disable-plugins",
                "--disable-extensions",
                "--disable-popup-blocking");

        capabilities.setCapability(ChromeOptions.CAPABILITY, chOptions);
        capabilities.setCapability("applicationCacheEnable", false);
        return capabilities;
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxProfile.setPreference("browser.autofocus", true);
        firefoxProfile.setPreference("dom.disable_open_during_load", false);
        capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        capabilities.setCapability("marionette", true);
        return capabilities;
    }

    private DesiredCapabilities getInternetExplorerCapabilities() {
        final DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        InternetExplorerOptions ieOptions = new InternetExplorerOptions();

        ieOptions.requireWindowFocus();
        ieOptions.merge(capabilities);
        capabilities.setCapability("requireWindowFocus", true);
        return capabilities;
    }

    private DesiredCapabilities getSafariCapabilities() {
        final DesiredCapabilities capabilities = DesiredCapabilities.safari();

        SafariOptions safariOptions = new SafariOptions();

        capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        capabilities.setCapability("autoAcceptAlerts", true);
        return capabilities;
    }
}
