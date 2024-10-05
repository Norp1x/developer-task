package com.google.service;

import com.google.service.exception.ErrorResponse;
import com.google.service.exception.NoConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GoogleServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoConnectionException.class)
    public ResponseEntity<Object> handleNoConnectionException(NoConnectionException noConnectionException) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(noConnectionException.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
