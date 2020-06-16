package com.hcl.onetest.exception;

import org.springframework.http.HttpStatus;

public class NoResourceException extends Throwable {

    private String errorMessage;

    public NoResourceException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
