package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    ArticleService articleService;

    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Article> latest5Articles = articleService.findLatest5();
        model.addAttribute("latest5articles", latest5Articles);
        List<Article> latest3Articles = latest5Articles.stream()
                .limit(3)
                .collect(Collectors.toList());
        model.addAttribute("latest3articles", latest3Articles);
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
