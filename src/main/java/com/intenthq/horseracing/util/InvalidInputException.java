package com.intenthq.horseracing.util;

/**
 * Created by iuliana on 6/26/14.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
