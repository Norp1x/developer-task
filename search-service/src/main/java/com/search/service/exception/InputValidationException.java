package com.search.service.exception;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom exception class representing an input validation error.
 * This exception is thrown when the input does not meet specified validation criteria.
 */
public class InputValidationException extends Throwable {

    public InputValidationException() {
        super("Wrong input. Input can't be blank and must be between 2 and 50 characters");
    }
}
