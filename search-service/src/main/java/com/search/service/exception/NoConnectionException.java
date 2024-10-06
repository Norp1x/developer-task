package com.search.service.exception;

public class NoConnectionException extends RuntimeException {

    private static final String message = "Cannot resolve connection to Google-Service";

    public NoConnectionException() {
        super(message);
    }
}
