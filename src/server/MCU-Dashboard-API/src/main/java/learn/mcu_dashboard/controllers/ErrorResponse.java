package learn.mcu_dashboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import learn.mcu_dashboard.domain.Result;
import learn.mcu_dashboard.domain.ResultType;
import learn.mcu_dashboard.models.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static <T> ResponseEntity<Object> build(Result<T> result) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (result.getType() == null || result.getType() == ResultType.INVALID) {
            status = HttpStatus.BAD_REQUEST;
        } else if (result.getType() == ResultType.NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result.getMessages(), status);
    }

}
