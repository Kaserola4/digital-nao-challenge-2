package com.pikolinc.googlescholar.controller;

import com.pikolinc.googlescholar.domain.dto.ArticleDto;
import com.pikolinc.googlescholar.domain.entity.Article;
import com.pikolinc.googlescholar.service.ArticleService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save/{authorId}/{articlePosition}")
    public ResponseEntity<Article> saveArticle(
            @PathVariable String authorId,
            @PathVariable int articlePosition) {

        Article savedArticle = articleService.saveAuthorArticle(authorId, articlePosition);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
}
