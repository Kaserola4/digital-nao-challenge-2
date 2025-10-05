package com.pikolinc.googlescholar.exception.author;

import com.pikolinc.googlescholar.exception.ResponseException;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when required properties are missing or invalid for author operations.
 * <p>
 * This exception is typically thrown during validation when mandatory parameters
 * such as author ID or API key are null, empty, or otherwise invalid. It indicates
 * a client error where the request cannot be processed due to missing or malformed data.
 * </p>
 * <p>
 * When thrown, this exception results in an HTTP 400 (Bad Request) response
 * being sent to the client.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see ResponseException
 */
public class AuthorMissingPropertyException extends ResponseException {

    /**
     * Constructs a new AuthorMissingPropertyException with the specified detail message.
     *
     * @param message the detail message indicating which property is missing or invalid
     */
    public AuthorMissingPropertyException(String message) {
        super(message);
    }

    /**
     * Returns the HTTP status code for this exception.
     *
     * @return {@link HttpStatus#BAD_REQUEST} (400)
     */
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}