package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.services.ArticleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ArticleController {

    private ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("article/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        return "articleform";
    }

    @PostMapping("article/new")
    public String addArticle(Model model, @Valid Article article, BindingResult br) {
        articleService.add(article);
        model.addAttribute("article", new Article());
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
