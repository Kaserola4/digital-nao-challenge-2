package com.pikolinc.googlescholar.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record ArticleResponseDto(
        Long id,
        String title,
        List<String> authors,
        LocalDate publicationDate,
        String summary,
        String link,
        List<String> keywords,
        Integer citedBy
) {

    public static ArticleResponseDto from(
            Long id,
            String title,
            String authorsStr,
            LocalDate publicationDate,
            String summary,
            String link,
            String keywordsStr,
            Integer citedBy
    ) {
        List<String> authorsList = authorsStr != null && !authorsStr.isBlank()
                ? List.of(authorsStr.split(",\\s*"))
                : List.of();

        List<String> keywordsList = keywordsStr != null && !keywordsStr.isBlank()
                ? List.of(keywordsStr.split(",\\s*"))
                : List.of();

        return new ArticleResponseDto(
                id,
                title,
                authorsList,
                publicationDate,
                summary,
                link,
                keywordsList,
                citedBy
        );
    }
}