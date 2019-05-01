package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("article/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        Set<Category> list = new HashSet<Category>();
        model.addAttribute("categories", list);
        return "articleform";
    }

    @PostMapping("article")
    public String saveArticle(Article article) {
        articleService.saveArticle(article);
        return "redirect:/article/show/" + article.getId();
    }

    @GetMapping("article/show/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "articleshow";
    }

    @GetMapping("articles")
    public String listArticle(Model model) {
        model.addAttribute("articles", articleService.listAllArticles());
        return "articles";
    }

    @GetMapping("article/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "articleform";
    }

    @GetMapping("article/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }


}
