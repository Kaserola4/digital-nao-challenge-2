package com.pikolinc.googlescholar.dto;

import java.util.List;

public record AuthorResponse(
        AuthorDetailsDto author,
        List<InterestDto> interests,
        List<ArticleDto> articles,
        PublicAccessDto publicAccess,
        PaginationDto pagination
) {

    public record AuthorDetailsDto(
            String name,
            String affiliations,
            String email,
            String thumbnailUrl
    ){}

    public record InterestDto(
            String title,
            String link
    ) {}

    public record PublicAccessDto(
            String link,
            int available,
            int notAvailable
    ) {}

    public record PaginationDto(
            String nextPageUrl
    ) {}
}