package com.pikolinc.googlescholar.exception.author;

import com.pikolinc.googlescholar.exception.ResponseException;
import org.springframework.http.HttpStatus;

public class AuthorMissingPropertyException extends ResponseException {
    public AuthorMissingPropertyException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
