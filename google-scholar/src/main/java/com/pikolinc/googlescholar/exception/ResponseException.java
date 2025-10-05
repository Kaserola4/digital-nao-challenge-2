package com.pikolinc.googlescholar.exception;

import org.springframework.http.HttpStatus;

/**
 * Base exception class for all custom exceptions in the Google Scholar API.
 * <p>
 * This abstract class extends {@link RuntimeException}, making all custom exceptions
 * unchecked. Each subclass must define its own HTTP status code by implementing
 * the {@link #getHttpStatus()} method, which is used by the global exception handler
 * to generate appropriate HTTP responses.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see RuntimeException
 * @see com.pikolinc.googlescholar.exception.GlobalExceptionHandlerAdvice
 */
public abstract class ResponseException extends RuntimeException {

    /**
     * Constructs a new ResponseException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResponseException(String message) {
        super(message);
    }

    /**
     * Returns the HTTP status code associated with this exception.
     * <p>
     * This method must be implemented by each concrete subclass to define
     * the appropriate HTTP status code that should be returned to the client
     * when this exception is thrown.
     * </p>
     *
     * @return the HTTP status code for this exception
     */
    public abstract HttpStatus getHttpStatus();
}