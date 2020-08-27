package com.tomash.gombosh.web.exception;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.LF;

/**
 * @author Tomash Gombosh
 */
public class UnsupportedBrowserException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -944053063920967924L;

    public UnsupportedBrowserException(final String message) {
        super(message);
    }

    public UnsupportedBrowserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnsupportedBrowserException(final String message, final String errorMessage) {
        super(message + LF + "Cause: " + errorMessage);
    }
}
