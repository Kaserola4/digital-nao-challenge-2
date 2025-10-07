package com.pikolinc.googlescholar.service;

import com.pikolinc.googlescholar.domain.dto.AuthorResponseDto;
import com.pikolinc.googlescholar.exception.MissingPropertyException;
import com.pikolinc.googlescholar.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service for interacting with Google Scholar Author data through the SerpAPI.
 * <p>
 * This service provides methods to retrieve author information from Google Scholar
 * using the SerpAPI service. It handles API communication, URL construction,
 * and response validation.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see AuthorResponseDto
 * @see RestTemplate
 */
@Service
public class AuthorService {

    private final RestTemplate restTemplate;

    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.base.url}")
    private String baseUrl;

    /**
     * Constructs a new AuthorService with the specified RestTemplate.
     *
     * @param restTemplate the REST client used for HTTP requests to SerpAPI
     */
    public AuthorService(RestTemplate restTemplate) {
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
     * @return an {@link AuthorResponseDto} containing the author's detailed information including
     *         profile data, interests, articles, and pagination information
     * @throws MissingPropertyException if authorId is null, empty, or if the API key
     *         is not properly configured
     * @throws NotFoundException if no author exists with the provided ID or if the
     *         API returns an empty author object
     * @see AuthorResponseDto
     */
    public AuthorResponseDto getAuthorById(String authorId) {
        if (authorId == null || authorId.isEmpty())
            throw new MissingPropertyException("Missing [authorId]");
        if (apiKey == null || apiKey.isEmpty())
            throw new MissingPropertyException("Missing [apiKey]");

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("engine", "google_scholar_author")
                .queryParam("author_id", authorId)
                .queryParam("api_key", apiKey)
                .queryParam("hl", "en")
                .toUriString();

        AuthorResponseDto authorResponseDto = restTemplate.getForObject(url, AuthorResponseDto.class);

        if (authorResponseDto != null && authorResponseDto.author() == null)
            throw new NotFoundException("Author not found: " + authorId);

        return authorResponseDto;
    }
}