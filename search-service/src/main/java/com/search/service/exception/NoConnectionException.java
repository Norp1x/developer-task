package com.search.service.exception;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom runtime exception class representing a failure to connect to the Google-Service.
 * This exception is thrown when the application cannot establish a connection to the external service.
 */
public class NoConnectionException extends RuntimeException {

    private static final String message = "Cannot resolve connection to Google-Service";

    public NoConnectionException() {
        super(message);
    }
}
