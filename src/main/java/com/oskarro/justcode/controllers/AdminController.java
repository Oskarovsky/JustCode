package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleService;
import com.oskarro.justcode.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    ArticleService articleService;
    CategoryService categoryService;

    public AdminController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String indexAdmin(Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "admin/index";
    }

    @GetMapping("/profile")
    public String profileAdmin(Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "admin/profile";
    }
}
