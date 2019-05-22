package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleService;
import com.oskarro.justcode.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    ArticleService articleService;
    CategoryService categoryService;

    public IndexController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Article> latest5Articles = articleService.findLatest5();
        model.addAttribute("latest5articles", latest5Articles);

        List<Article> latest3Articles = latest5Articles.stream()
                .limit(3)
                .collect(Collectors.toList());
        model.addAttribute("latest3articles", latest3Articles);

        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "general/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }


}
