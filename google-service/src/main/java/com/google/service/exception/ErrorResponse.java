package com.google.service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Represents an error response sent to the client when an exception occurs.
 * This class contains a timestamp of the error and a list of error messages.
 */
@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;
    private List<String> message;
    private HttpStatus httpStatus;

    public ErrorResponse(List<String> message, HttpStatus httpStatus) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
