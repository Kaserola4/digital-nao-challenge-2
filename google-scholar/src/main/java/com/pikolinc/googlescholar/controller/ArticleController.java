package com.pikolinc.googlescholar.controller;

import com.pikolinc.googlescholar.domain.entity.Article;
import com.pikolinc.googlescholar.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();

        if (articles.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.status(HttpStatus.OK).body(articles);
    }

    @PostMapping("/{authorId}/{articlePosition}")
    public ResponseEntity<Article> saveArticle(
            @PathVariable String authorId,
            @PathVariable int articlePosition) {

        Article savedArticle = articleService.saveAuthorArticle(authorId, articlePosition);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
