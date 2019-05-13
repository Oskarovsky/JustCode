package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;

import java.util.List;

public interface ArticleService {

    List<Article> getAll();

    Article findById(Long id);

    List<Category> getAllCategories(Long id);

    Article save(Article article);

    void deleteArticle(Long id);

    void add(Article article);

    List<Article> findLatest5();


}
