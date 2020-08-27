package com.tomash.gombosh.web.utils;

/**
 * @author Tomash Gombosh
 */
public final class ApplicationProperties {
    public static final String BROWSER = System.getProperty("browser", "Chrome");
    public static final String BASE_URL = System.getProperty("base.url", "http://automationpractice.com/index.php");
    public static final String BASE_USER = System.getProperty("base.user", "blackjneco+24/7@gmail.com");
    public static final String BASE_PASSWORD = System.getProperty("base.user.password", "test123");

    private ApplicationProperties() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
