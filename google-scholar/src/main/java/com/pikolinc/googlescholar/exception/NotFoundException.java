package com.pikolinc.googlescholar.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested Google Scholar author cannot be found.
 * <p>
 * This exception is thrown when the SerpAPI returns a valid response but
 * the author data is null or empty, indicating that no author exists with
 * the provided identifier.
 * </p>
 * <p>
 * When thrown, this exception results in an HTTP 404 (Not Found) response
 * being sent to the client.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see ResponseException
 */
public class NotFoundException extends ResponseException {

    /**
     * Constructs a new AuthorNotFoundException with the specified detail message.
     *
     * @param message the detail message, typically including the author ID that was not found
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Returns the HTTP status code for this exception.
     *
     * @return {@link HttpStatus#NOT_FOUND} (404)
     */
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}