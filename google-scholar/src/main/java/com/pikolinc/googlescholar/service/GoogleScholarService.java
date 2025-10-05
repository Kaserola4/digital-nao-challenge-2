package com.pikolinc.googlescholar.service;

import com.pikolinc.googlescholar.dto.AuthorResponse;
import com.pikolinc.googlescholar.exception.author.AuthorMissingPropertyException;
import com.pikolinc.googlescholar.exception.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleScholarService {
    private final RestTemplate restTemplate;

    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.base.url:https://serpapi.com/search.json}")
    private String baseUrl;

    public GoogleScholarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthorResponse getAuthorById(String authorId) {
             if (authorId == null || authorId.isEmpty()) throw new AuthorMissingPropertyException("Missing [authorId]");
        if (apiKey == null || apiKey.isEmpty()) throw new AuthorMissingPropertyException("Missing [apiKey]");

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