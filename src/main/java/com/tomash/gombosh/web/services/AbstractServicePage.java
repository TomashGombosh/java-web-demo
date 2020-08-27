package com.tomash.gombosh.web.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Tomash Gombosh
 */
@Log4j
public abstract class AbstractServicePage {
    protected WebDriver driver;

    protected AbstractServicePage(final WebDriver driver) {
        this.driver = driver;
    }

    public abstract boolean pageIsDisplayed();

    public void enterText(final PageElement pageElement, final String text) {
        this.enterText(pageElement, text, true);
    }

    public void enterText(final PageElement pageElement, String text, final boolean clearField) {
        log.info("Entering text \"" + text + "\" to element: " + pageElement.getName());
        this.find(pageElement).click();
        if (clearField) {
            this.find(pageElement).clear();
        }
        this.find(pageElement).sendKeys(text);
    }

    public void click(final PageElement pageElement) {
        log.info("Clicking on element: " + pageElement.getName());
        this.find(pageElement).click();
    }

    public WebElement find(final By element) {
        return this.driver.findElement(element);
    }

    public WebElement find(final PageElement element) {
        return this.find(element.getLocator());
    }

    public List<PageElement> getElements() {
        final List<PageElement> elements = new ArrayList<PageElement>();
        for (final Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().getSimpleName().equals("PageElement")) {
                try {
                    field.setAccessible(true);
                    elements.add((PageElement) field.get(PageElement.class));
                    field.setAccessible(false);
                } catch (IllegalAccessException ignored) {
                    System.out.println(ignored.toString());
                }
            }
        }
        return elements;
    }

    public List<PageElement> getRequiredElements() {
        final ArrayList<PageElement> requiredElements = new ArrayList<PageElement>();
        for (final PageElement ele : this.getElements()) {
            if (ele.isRequired()) {
                requiredElements.add(ele);
            }
        }
        return requiredElements;
    }

    public ArrayList<PageElement> getMissingRequiredElements(List<PageElement> requiredElements) {
        final ArrayList<PageElement> elements = new ArrayList<PageElement>(requiredElements);
        for (final PageElement ele : requiredElements) {
            if (this.isElementPresent(ele)) {
                elements.remove(ele);
            } else {
                log.info("Missed required element: " + ele.getName());
            }
        }
        return elements;
    }

    protected boolean allRequiredElementDisplayed() {
        log.info("Checking if all required elements present on page");
        return this.getMissingRequiredElements(this.getRequiredElements()).isEmpty();
    }

    public boolean isElementPresent(final By element) {
        boolean elementFound;
        try {
            this.find(element);
            elementFound = true;
        } catch (NoSuchElementException e) {
            elementFound = false;
        }
        return elementFound;
    }

    public boolean isElementPresent(final PageElement element) {
        return this.isElementPresent(element.getLocator());
    }

    public void waitToBeVisible(final By element, final int timeout) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(this.driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoreAll(Arrays.asList(
                        ElementNotVisibleException.class,
                        NoSuchElementException.class,
                        StaleElementReferenceException.class,
                        WebDriverException.class));
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver input) {
                return input.findElement(element);
            }
        });
    }

    public void waitToBeVisible(final PageElement element) {
        this.waitToBeVisible(element.getLocator(), 30);
    }

    public void waitToBeClickable(final By element, final int timeout) {
        final WebDriverWait wait = new WebDriverWait(this.driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void waitToBeClickable(final PageElement element) {
        this.waitToBeClickable(element.getLocator(), 30);
    }

}
