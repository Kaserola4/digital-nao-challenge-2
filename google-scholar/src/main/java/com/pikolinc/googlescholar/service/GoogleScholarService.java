package com.pikolinc.googlescholar.service;

import com.pikolinc.googlescholar.dto.AuthorDto;
import com.pikolinc.googlescholar.exception.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @SuppressWarnings("unchecked")
    public AuthorDto getAuthorById(String authorId) throws AuthorNotFoundException {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("engine", "google_scholar_author")
                .queryParam("author_id", authorId)
                .queryParam("api_key", apiKey)
                .queryParam("hl", "en")
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("author"))
            throw new AuthorNotFoundException("Author not found: " + authorId);

        return mapToAuthorDto(response, authorId);
    }

    @SuppressWarnings("unchecked")
    private AuthorDto mapToAuthorDto(Map<String, Object> response, String scholarId) {
        Map<String, Object> author = (Map<String, Object>) response.get("author");

        return new AuthorDto(
                scholarId,
                (String) author.get("name"),
                (String) author.get("affiliations"),
                (String) author.get("email"),
                (String) author.get("thumbnail"),
                mapInterests((List<Map<String, Object>>) author.get("interests")),
                mapArticles((List<Map<String, Object>>) response.get("articles")),
                mapPublicAccess((Map<String, Object>) response.get("public_access")),
                mapPagination((Map<String, Object>) response.get("serpapi_pagination"))
        );
    }

    private List<AuthorDto.InterestDto> mapInterests(List<Map<String, Object>> interests) {
        if (interests == null) return List.of();
        return interests.stream()
                .map(i -> new AuthorDto.InterestDto(
                        (String) i.get("title"),
                        (String) i.get("link")
                ))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<AuthorDto.ArticleDto> mapArticles(List<Map<String, Object>> articles) {
        if (articles == null) return List.of();
        return articles.stream()
                .map(a -> {
                    Map<String, Object> citedBy = (Map<String, Object>) a.get("cited_by");
                    return new AuthorDto.ArticleDto(
                            (String) a.get("title"),
                            (String) a.get("link"),
                            (String) a.get("citation_id"),
                            (String) a.get("authors"),
                            (String) a.get("publication"),
                            (String) a.get("year"),
                            citedBy != null ?
                                    new AuthorDto.ArticleDto.CitedByDto(
                                            (Integer) citedBy.get("value"),
                                            (String) citedBy.get("link")
                                    ) : null
                    );
                })
                .collect(Collectors.toList());
    }

    private AuthorDto.PublicAccessDto mapPublicAccess(Map<String, Object> publicAccess) {
        if (publicAccess == null) return null;

        return new AuthorDto.PublicAccessDto(
                (String) publicAccess.get("link"),
                (Integer) publicAccess.get("available"),
                (Integer) publicAccess.get("not_available")
        );
    }

    private AuthorDto.PaginationDto mapPagination(Map<String, Object> pagination) {
        if (pagination == null) return null;

        return new AuthorDto.PaginationDto((String) pagination.get("next"));
    }
}