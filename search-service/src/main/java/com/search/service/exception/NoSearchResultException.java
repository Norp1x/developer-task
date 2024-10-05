package com.search.service.exception;

public class NoSearchResultException extends RuntimeException {

    public NoSearchResultException(Long id) {
        super("The id '" + id + "' does not exist in database");
    }
}
