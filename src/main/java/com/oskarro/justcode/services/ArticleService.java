package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.domains.Comment;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAll();

    Optional<Article> findById(Long id);

    List<Category> getAllCategories(Long id);

    Article save(Article article);

    void deleteArticle(Long id);

    void add(Article article);

    List<Article> findLatest5();

    List<Comment> getAllComments(Long id);

}
