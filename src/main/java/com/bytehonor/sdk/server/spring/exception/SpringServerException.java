package com.bytehonor.sdk.server.spring.exception;

/**
 * @author lijianqiang
 *
 */
public class SpringServerException extends RuntimeException {

    private static final long serialVersionUID = 619381575400869623L;

    public SpringServerException() {
        super();
    }

    public SpringServerException(String message) {
        super(message);
    }

    public SpringServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
