package com.pikolinc.googlescholar.dto;

import java.util.List;

/**
 * Data Transfer Object representing a complete Google Scholar author response.
 * <p>
 * This record encapsulates all information about a Google Scholar author including
 * their profile details, research interests, publications, public access information,
 * and pagination data for retrieving additional results.
 * </p>
 *
 * @param author detailed author profile information
 * @param interests list of the author's research interests
 * @param articles list of the author's publications
 * @param publicAccess information about open access availability of the author's work
 * @param pagination pagination data for retrieving additional articles
 * @author Pikolinc
 * @version 1.0
 */
public record AuthorResponse(
        AuthorDetailsDto author,
        List<InterestDto> interests,
        List<ArticleDto> articles,
        PublicAccessDto publicAccess,
        PaginationDto pagination
) {

    /**
     * Represents the author's basic profile information.
     *
     * @param name the author's full name
     * @param affiliations the author's institutional affiliations
     * @param email the author's email address (may be null if not public)
     * @param thumbnailUrl URL to the author's profile picture
     */
    public record AuthorDetailsDto(
            String name,
            String affiliations,
            String email,
            String thumbnailUrl
    ) {}

    /**
     * Represents a research interest or field of study.
     *
     * @param title the name of the research interest
     * @param link URL to Google Scholar search results for this interest
     */
    public record InterestDto(
            String title,
            String link
    ) {}

    /**
     * Represents public access information for the author's publications.
     *
     * @param link URL to view public access details on Google Scholar
     * @param available number of publications available via public access
     * @param notAvailable number of publications not available via public access
     */
    public record PublicAccessDto(
            String link,
            int available,
            int notAvailable
    ) {}

    /**
     * Represents pagination information for retrieving additional articles.
     *
     * @param nextPageUrl URL to fetch the next page of articles (null if no more pages)
     */
    public record PaginationDto(
            String nextPageUrl
    ) {}
}