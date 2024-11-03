package com.google.service;

import com.google.service.exception.ErrorResponse;
import com.google.service.exception.NoConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Global exception handler for handling exceptions related to Google API service.
 * This class provides centralized exception handling for specific exceptions,
 * returning custom error responses to the client.
 */
@ControllerAdvice
public class GoogleServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoConnectionException.class)
    public ResponseEntity<Object> handleNoConnectionException(NoConnectionException noConnectionException) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(noConnectionException.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
