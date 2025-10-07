package com.pikolinc.googlescholar.repository;

import com.pikolinc.googlescholar.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByTitle(String title);

    Optional<Article> findByLink(String link);

    boolean existsByTitle(String title);
    boolean existsByLink(String link);
}
