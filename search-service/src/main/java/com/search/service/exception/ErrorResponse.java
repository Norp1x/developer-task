package com.search.service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: A class representing an error response that provides details about the error
 * in the application. Used to communicate error information to the client.
 */
@Getter
@Setter
public class ErrorResponse {

    /**
     * The date and time when the error occurred.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;
    /**
     * A list of error messages describing the cause of the error.
     */
    private List<String> message;
    /**
     * The HTTP status representing the type of error (e.g., 404 Not Found, 500 Internal Server Error).
     */
    private HttpStatus httpStatus;

    public ErrorResponse(List<String> message, HttpStatus httpStatus) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
