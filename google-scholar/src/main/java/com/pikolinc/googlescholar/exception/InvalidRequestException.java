package com.pikolinc.googlescholar.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ResponseException {
    public InvalidRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
