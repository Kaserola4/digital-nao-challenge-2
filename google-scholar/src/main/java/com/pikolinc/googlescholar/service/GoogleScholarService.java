package com.pikolinc.googlescholar.service;

import com.pikolinc.googlescholar.domain.dto.author.AuthorResponse;
import com.pikolinc.googlescholar.exception.author.AuthorMissingPropertyException;
import com.pikolinc.googlescholar.exception.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service for interacting with Google Scholar data through the SerpAPI.
 * <p>
 * This service provides methods to retrieve author information from Google Scholar
 * using the SerpAPI service. It handles API communication, URL construction,
 * and response validation.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see AuthorResponse
 * @see RestTemplate
 */
@Service
public class GoogleScholarService {

    private final RestTemplate restTemplate;

    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.base.url}")
    private String baseUrl;

    /**
     * Constructs a new GoogleScholarService with the specified RestTemplate.
     *
     * @param restTemplate the REST client used for HTTP requests to SerpAPI
     */
    public GoogleScholarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves detailed information about a Google Scholar author by their unique identifier.
     * <p>
     * This method queries the SerpAPI Google Scholar Author API to fetch comprehensive
     * author information including name, affiliations, publications, citations, and other
     * academic metrics. The method validates input parameters and API configuration before
     * making the request.
     * </p>
     *
     * @param authorId the unique Google Scholar author identifier (e.g., "4bahYMkAAAAJ")
     * @return an {@link AuthorResponse} containing the author's detailed information including
     *         profile data, interests, articles, and pagination information
     * @throws AuthorMissingPropertyException if authorId is null, empty, or if the API key
     *         is not properly configured
     * @throws AuthorNotFoundException if no author exists with the provided ID or if the
     *         API returns an empty author object
     * @see AuthorResponse
     */
    public AuthorResponse getAuthorById(String authorId) {
        if (authorId == null || authorId.isEmpty())
            throw new AuthorMissingPropertyException("Missing [authorId]");
        if (apiKey == null || apiKey.isEmpty())
            throw new AuthorMissingPropertyException("Missing [apiKey]");

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("engine", "google_scholar_author")
                .queryParam("author_id", authorId)
                .queryParam("api_key", apiKey)
                .queryParam("hl", "en")
                .toUriString();

        AuthorResponse authorResponse = restTemplate.getForObject(url, AuthorResponse.class);

        if (authorResponse != null && authorResponse.author() == null)
            throw new AuthorNotFoundException("Author not found: " + authorId);

        return authorResponse;
    }
}