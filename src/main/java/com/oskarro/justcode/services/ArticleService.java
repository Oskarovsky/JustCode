package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;

public interface ArticleService {

    Iterable<Article> listAllArticles();

    Article getArticleById(Integer id);

    Article saveArticle(Article article);
}
