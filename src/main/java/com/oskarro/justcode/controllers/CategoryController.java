package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleServiceImpl;
import com.oskarro.justcode.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    CategoryServiceImpl categoryService;
    ArticleServiceImpl articleService;

    public CategoryController(CategoryServiceImpl categoryService, ArticleServiceImpl articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public String getAllIndex(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesform";
    }

    @PostMapping("/add")
    public String addCategory(Model model, @Valid Category category, BindingResult br) {
        categoryService.add(category);
        model.addAttribute("category", new Category());
        return "categoriesform";
    }

    @GetMapping("/adds")
    public String addCategoryToArticle(Model model, @RequestParam(value = "id", required = true) Long id) {
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.getAll());
        return "articlesform";
    }

    @GetMapping("/relationAdd")
    public String addParameter(Model model, @RequestParam(value = "idArticle", required = true) Long idArticle,
                              @RequestParam(value = "idCategory", required = true) Long idCategory) {
        Category cat = categoryService.findById(idCategory);
        Article art = articleService.getArticleById(idArticle);
        cat.getArticles().add(art);
        categoryService.save(cat);
        model.addAttribute("id", idArticle);
        model.addAttribute("categories", categoryService.getAll());
        return "articlesform";
    }

    @GetMapping("/getArticleCategory")
    public String getAllCategoriesArticle(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("categories", articleService.getAllCategories(id));
        return "categories";
    }

}












