package com.search.service.exception;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom runtime exception class representing the absence of a search result in the database.
 * This exception is thrown when a search result with the specified ID does not exist in the database.
 */
public class NoSearchResultException extends RuntimeException {

    public NoSearchResultException(Long id) {
        super("The id '" + id + "' does not exist in database");
    }
}
