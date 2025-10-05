package com.pikolinc.googlescholar.dto;

public record ArticleDto(
        String title,
        String link,
        String citationId,
        String authors,
        String publication,
        String year,
        CitedByDto citedBy
) {
    public record CitedByDto(
            int value,
            String link
    ) {
    }
}