package com.pikolinc.googlescholar.service;

import com.pikolinc.googlescholar.domain.dto.AuthorResponseDto;
import com.pikolinc.googlescholar.domain.entity.Article;
import com.pikolinc.googlescholar.exception.InvalidRequestException;
import com.pikolinc.googlescholar.exception.MissingPropertyException;
import com.pikolinc.googlescholar.exception.NotFoundException;
import com.pikolinc.googlescholar.mapper.ArticleMapper;
import com.pikolinc.googlescholar.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final RestTemplate restTemplate;
    private final ArticleRepository articleRepository;

    @Value("${serpapi.api.key}")
    private String apiKey;

    @Value("${serpapi.base.url}")
    private String baseUrl;

    public ArticleService(RestTemplate restTemplate, ArticleRepository articleRepository) {
        this.restTemplate = restTemplate;
        this.articleRepository = articleRepository;
    }

    public Article saveAuthorArticle(String authorId, int articlePosition) {
        if (authorId == null || authorId.isEmpty())
            throw new MissingPropertyException("Missing [authorId]");

        if  (articlePosition < 0)
            throw new InvalidRequestException("Article position cannot be lower than zero");

        if (apiKey == null || apiKey.isEmpty())
            throw new MissingPropertyException("Missing [apiKey]");

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("engine", "google_scholar_author")
                .queryParam("author_id", authorId)
                .queryParam("api_key", apiKey)
                .queryParam("hl", "en")
                .toUriString();

        AuthorResponseDto authorResponseDto = restTemplate.getForObject(url, AuthorResponseDto.class);

        if (authorResponseDto == null)
            throw new NotFoundException("Article not found");

        if (authorResponseDto.author() == null)
            throw new NotFoundException("Author not found: " + authorId);

        Article article = ArticleMapper.mapFromAuthor(authorResponseDto, articlePosition);

        if (article.getTitle() != null) {
            Optional<Article> existingArticle = articleRepository.findByTitle(article.getTitle());
            if (existingArticle.isPresent()) {
                return existingArticle.get();
            }
        }

        return articleRepository.save(article);
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new NotFoundException("Article not found with id: " + id);
        }

        articleRepository.deleteById(id);
    }
}