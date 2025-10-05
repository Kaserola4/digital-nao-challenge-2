package com.pikolinc.googlescholar.dto;

/**
 * Data Transfer Object representing a single academic article or publication.
 * <p>
 * This record contains metadata about a scholarly publication including
 * title, authors, publication venue, citation information, and links.
 * </p>
 *
 * @param title the title of the publication
 * @param link URL to the publication (may link to Google Scholar or external source)
 * @param citationId unique identifier for citation tracking
 * @param authors comma-separated list of author names
 * @param publication the venue where the article was published (journal, conference, etc.)
 * @param year the year of publication
 * @param citedBy citation count and link to citing articles
 * @author Pikolinc
 * @version 1.0
 */
public record ArticleDto(
        String title,
        String link,
        String citationId,
        String authors,
        String publication,
        String year,
        CitedByDto citedBy
) {
    /**
     * Represents citation information for an article.
     *
     * @param value the number of times this article has been cited
     * @param link URL to Google Scholar page showing all citing articles
     */
    public record CitedByDto(
            int value,
            String link
    ) {}
}