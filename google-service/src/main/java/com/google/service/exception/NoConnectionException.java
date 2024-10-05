package com.google.service.exception;

public class NoConnectionException extends RuntimeException {

    public NoConnectionException() {
        super("Cannot resolve connection");
    }
}
