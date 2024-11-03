package com.google.service.exception;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom runtime exception indicating a failure to establish a connection.
 * This exception is thrown when the application cannot connect to an external service.
 */
public class NoConnectionException extends RuntimeException {

    public NoConnectionException() {
        super("Cannot resolve connection");
    }
}
