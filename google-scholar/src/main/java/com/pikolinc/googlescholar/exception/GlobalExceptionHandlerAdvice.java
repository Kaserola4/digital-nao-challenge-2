package com.pikolinc.googlescholar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the Google Scholar API application.
 * <p>
 * This class intercepts exceptions thrown by controllers and services,
 * providing consistent error responses across the application.
 * Uses Spring's {@link RestControllerAdvice} to apply exception handling
 * to all REST controllers automatically.
 * </p>
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 */
@RestControllerAdvice
class GlobalExceptionHandlerAdvice {

    public record ErrorResponse(int status, String message) { }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleResponseException(ResponseException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), exception.getMessage());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please try again later."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}