package com.search.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom runtime exception class representing a failure to connect to the Google-Service.
 * This exception is thrown when the application cannot establish a connection to the external service.
 */
@Slf4j
public class NoConnectionException extends RuntimeException {

    private static final String message = "Cannot resolve connection to Google-Service";

    public NoConnectionException(RestClientException restClientException) {
        super(message);
        log.error(String.valueOf(restClientException));
    }
}
