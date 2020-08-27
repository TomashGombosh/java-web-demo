package com.tomash.gombosh.web.utils;

import lombok.extern.log4j.Log4j;

/**
 * @author Tomash Gombosh
 */
@Log4j
public final class Utils {

    private Utils() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static String getSystemName() {
        return System.getProperty("os.name");
    }

    public static void sleep(final long seconds) {
        try {
            log.info(String.format("Wait %s", seconds));
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            log.error("Exception log", e);
        }
    }
}
