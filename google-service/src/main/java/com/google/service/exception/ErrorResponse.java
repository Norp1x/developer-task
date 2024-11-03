package com.google.service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private List<String> message;

    public ErrorResponse(List<String> message) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
    }
}
