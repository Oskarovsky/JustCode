package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;

import java.util.List;

public interface ArticleService {

    Iterable<Article> listAllArticles();

    Article getArticleById(Long id);

    Article saveArticle(Article article);

    void deleteArticle(Long id);

    List<Category> getAllCategories(int id);


}
