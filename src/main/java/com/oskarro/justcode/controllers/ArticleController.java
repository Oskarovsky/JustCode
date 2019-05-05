package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleServiceImpl;
import com.oskarro.justcode.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(("/article"))
public class ArticleController{

    private ArticleServiceImpl articleService;
    private CategoryServiceImpl categoryService;

    public ArticleController(ArticleServiceImpl articleService, CategoryServiceImpl categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String listArticle(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "all_articles";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        return "add_article";
    }

    @PostMapping("/new")
    public String addArticle(Model model,
                             @Valid Article article, BindingResult br) {
        articleService.add(article);
        model.addAttribute("article", new Article());
        return "add_article";
    }

    @PostMapping("article")
    public String saveArticle(Article article) {
        articleService.save(article);
        return "redirect:/article/show/" + article.getId();
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "article_show";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "add_article";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/all_articles";
    }


}
