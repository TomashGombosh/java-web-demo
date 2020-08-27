package com.tomash.gombosh.web.config.driver;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.tomash.gombosh.web.exception.UnsupportedBrowserException;
import com.tomash.gombosh.web.services.Factory;

import static com.tomash.gombosh.web.utils.Utils.getSystemName;

/**
 * @author Tomash Gombosh
 */
@Log4j
@Setter
@Accessors(chain = true)
public class DriverFactory implements Factory<WebDriver, DriverFactory> {

    private Browsers browsers = Browsers.CHROME;

    @Override
    public WebDriver create() {
        final DesiredCapabilities capabilities = new CapabilityFactory().setBrowsers(browsers).create();
        switch (browsers) {
            case CHROME:
                initChromeDriverPath();
                return new ChromeDriver(capabilities);
            case FIREFOX:
                initFirefoxDriverPath();
                return new FirefoxDriver(capabilities);
            case SAFARI:
                initSafariDriverPath();
                return new SafariDriver(capabilities);
            case IE:
                initInternetExplorerDriverPath();
                return new InternetExplorerDriver(capabilities);
            default:
                throw new UnsupportedBrowserException("Sorry, but that framework do not support browser", browsers.name());

        }
    }

    /**
     * initChromeDriverPath method initialize chrome driver on following OS
     */
    private void initChromeDriverPath() {
        String chromeDriverPath;
        if (getSystemName().contains("Win")) {
            chromeDriverPath = String.format("%s/src/main/resources/drivers/windows/chromedriver.exe", System.getProperty("user.dir"));
        } else if (getSystemName().contains("Mac")) {
            chromeDriverPath = String.format("%s/src/main/resources/drivers/mac/chromedriver", System.getProperty("user.dir"));
        } else {
            chromeDriverPath = String.format("%s/src/main/resources/drivers/linux/chromedriver", System.getProperty("user.dir"));
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    /**
     * initFirefoxDriverPath method initialize firefox driver on following OS
     */
    private void initFirefoxDriverPath() {
        String firefoxDriverPath;
        if (getSystemName().contains("Win")) {
            firefoxDriverPath = String.format("%s/src/main/resources/drivers/windows/geckodriver.exe", System.getProperty("user.dir"));
        } else if (getSystemName().contains("Mac")) {
            firefoxDriverPath = String.format("%s/src/main/resources/drivers/mac/geckodriver", System.getProperty("user.dir"));
        } else {
            firefoxDriverPath = String.format("%s/src/main/resources/drivers/linux/geckodriver", System.getProperty("user.dir"));
        }
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
    }

    /**
     * initSafariDriverPath method initialize safari driver on following OS
     */
    private void initSafariDriverPath() {
        String safariDriverPath;
        if (getSystemName().contains("Mac")) {
            safariDriverPath = String.format("%s/src/main/resources/drivers/mac/SafariDriver.safariextz", System.getProperty("user.dir"));
            System.setProperty("webdriver.safari.driver", safariDriverPath);
        } else {
            log.info("Safari doesn't supported by Windows and Linux");
        }
    }

    /**
     * initInternetExplorerDriverPath method initialize ie driver on following OS
     */
    private void initInternetExplorerDriverPath() {
        String internetExplorerPath;

        if (getSystemName().contains("Win")) {
            internetExplorerPath = String.format("%s/src/main/resources/drivers/windows/IEDriverServer.exe", System.getProperty("user.dir"));
            System.setProperty("webdriver.ie.driver", internetExplorerPath);
        } else {
            log.info("IE doesn't supported by Linux");
        }
    }
}
