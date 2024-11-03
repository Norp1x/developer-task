package com.search.service;

import com.search.service.exception.ErrorResponse;
import com.search.service.exception.InputValidationException;
import com.search.service.exception.NoConnectionException;
import com.search.service.exception.NoSearchResultException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Global exception handler for the SearchService application.
 * This class provides centralized exception handling across all controllers,
 * handling specific exceptions and returning custom error responses.
 */
@ControllerAdvice
public class SearchServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSearchResultException.class)
    public ResponseEntity<Object> handleNoSearchResultException(NoSearchResultException noSearchResultException) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(noSearchResultException.getMessage()), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(errors, HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(NoConnectionException.class)
    public ResponseEntity<Object> handleNoConnectionException(NoConnectionException noConnectionException) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(noConnectionException.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<Object> handleInputValidationException(InputValidationException inputValidationException) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(inputValidationException.getMessage()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
