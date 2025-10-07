package com.pikolinc.googlescholar.controller;

import com.pikolinc.googlescholar.domain.dto.AuthorResponseDto;
import com.pikolinc.googlescholar.exception.NotFoundException;
import com.pikolinc.googlescholar.exception.MissingPropertyException;
import com.pikolinc.googlescholar.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST controller for managing Google Scholar author information endpoints.
 * <p>
 * This controller exposes HTTP endpoints for retrieving author data from Google Scholar.
 * All responses are returned in JSON format.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see AuthorService
 * @see AuthorResponseDto
 */
@RestController
@RequestMapping("/authors")
class AuthorController {

    private final AuthorService googleScholarService;

    /**
     * Constructs a new AuthorController with the specified GoogleScholarService.
     *
     * @param googleScholarService the service used to retrieve author information
     */
    AuthorController(AuthorService googleScholarService) {
        this.googleScholarService = googleScholarService;
    }

    /**
     * Retrieves author information by Google Scholar author ID.
     * <p>
     * This endpoint accepts a Google Scholar author ID as a path variable and returns
     * comprehensive information about the author including their profile, publications,
     * interests, and citation metrics.
     * </p>
     * <p>
     * Example request: {@code GET /author/4bahYMkAAAAJ}
     * </p>
     *
     * @param authorId the unique Google Scholar author identifier
     * @return an {@link AuthorResponseDto} containing the author's complete information
     * @throws NotFoundException
     *         if no author exists with the provided ID (HTTP 404)
     * @throws MissingPropertyException
     *         if the authorId is null or empty (HTTP 400)
     */
    @GetMapping("/{authorId}")
    public AuthorResponseDto getAuthorById(@PathVariable String authorId) {
        return googleScholarService.getAuthorById(authorId);
    }
}