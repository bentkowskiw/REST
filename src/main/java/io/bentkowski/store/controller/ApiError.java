package io.bentkowski.store.controller;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private List<ApiValidationError> validationErrorList;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }



    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private HttpStatus httpStatus;

    private ApiError() {
        timestamp = LocalDateTime.now();
        validationErrorList = new ArrayList<>();
    }

    public void addApiValidationError(ApiValidationError error) {
        this.validationErrorList.add(error);
    }

    public ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
