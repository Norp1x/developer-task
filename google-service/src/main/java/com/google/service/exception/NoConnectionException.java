package com.google.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Custom runtime exception indicating a failure to establish a connection.
 * This exception is thrown when the application cannot connect to an external service.
 */
@Slf4j
public class NoConnectionException extends RuntimeException {

    public NoConnectionException(RestClientException restClientException) {
        super("Cannot resolve connection");
        log.error(String.valueOf(restClientException));
    }
}
