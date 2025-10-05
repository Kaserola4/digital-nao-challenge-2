package com.pikolinc.googlescholar.controller;

import com.pikolinc.googlescholar.domain.dto.author.AuthorResponse;
import com.pikolinc.googlescholar.service.GoogleScholarService;
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
 * @see GoogleScholarService
 * @see AuthorResponse
 */
@RestController
@RequestMapping("/author")
class AuthorController {

    private final GoogleScholarService googleScholarService;

    /**
     * Constructs a new AuthorController with the specified GoogleScholarService.
     *
     * @param googleScholarService the service used to retrieve author information
     */
    AuthorController(GoogleScholarService googleScholarService) {
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
     * @return an {@link AuthorResponse} containing the author's complete information
     * @throws com.pikolinc.googlescholar.exception.author.AuthorNotFoundException
     *         if no author exists with the provided ID (HTTP 404)
     * @throws com.pikolinc.googlescholar.exception.author.AuthorMissingPropertyException
     *         if the authorId is null or empty (HTTP 400)
     */
    @GetMapping("/{authorId}")
    public AuthorResponse getAuthorById(@PathVariable String authorId) {
        return googleScholarService.getAuthorById(authorId);
    }
}