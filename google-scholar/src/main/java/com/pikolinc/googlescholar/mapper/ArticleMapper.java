package com.pikolinc.googlescholar.mapper;

import com.pikolinc.googlescholar.domain.dto.ArticleDto;
import com.pikolinc.googlescholar.domain.dto.AuthorResponseDto;
import com.pikolinc.googlescholar.domain.entity.Article;
import com.pikolinc.googlescholar.exception.InvalidRequestException;

import java.time.LocalDate;

public class ArticleMapper {
    public static Article mapFromAuthor(AuthorResponseDto authorResponseDto, int articlePosition) {
        if(articlePosition >= authorResponseDto.articles().size())
            throw new InvalidRequestException("No article at position " + articlePosition);

        final ArticleDto articleDto = authorResponseDto.articles().get(articlePosition);

        return Article.builder()
                .title(articleDto.title())
                .authors(articleDto.authors())
                .publicationDate(LocalDate.now())
                .summary("No summary")
                .link(articleDto.link())
                .keywords("No keywords")
                .citedBy(articleDto.cited_by().value())
                .build();
    }
}
