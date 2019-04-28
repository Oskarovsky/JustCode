package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;

import java.util.Optional;

public interface ArticleService {

    Iterable<Article> listAllArticles();

    Optional<Article> getArticleById(Long id);

    Article saveArticle(Article article);

    void deleteArticle(Long id);
}
