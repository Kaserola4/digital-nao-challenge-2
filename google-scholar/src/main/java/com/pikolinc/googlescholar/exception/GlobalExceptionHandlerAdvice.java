package com.pikolinc.googlescholar.exception;

import com.pikolinc.googlescholar.exception.author.AuthorNotFoundException;
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

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthorNotFound(AuthorNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}