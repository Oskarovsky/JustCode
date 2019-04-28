package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {

    @GetMapping("article/new")
    public String newArticle(Model model) {
        model.addAttribute("product", new Article());
        return "articleform";
    }

    @PostMapping("article")
    public String saveArticle(Article article) {
        articleService.saveArticle(article);
        return "redirect:/article/" + article.getId();
    }

    @GetMapping("article/{id}")
    public String showArticle(@PathVariable Integer id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "articleshow";
    }

    @GetMapping("articles")
    public String listArticle(Model model) {
        model.addAttribute("articles", articleService.listAllArticles());
        return "articles";
    }

    @GetMapping("article/edit/{id}")
    public String editArticle(@PathVariable Integer id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "articleform";
    }

    @GetMapping("article/delete/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }

}
