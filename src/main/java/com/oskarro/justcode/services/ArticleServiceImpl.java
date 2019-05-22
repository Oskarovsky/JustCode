package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories(Long id) {
        return articleRepository.getAllCategories(id);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article save(Article article) {
        article.setLastUpdatedAt(new Date());
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void add(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> findLatest5() {
        return articleRepository.findAll().stream()
                .sorted((a, b) -> b.getPostedAt().compareTo(a.getPostedAt()))
                .limit(5)
                .collect(Collectors.toList());
    }



}
