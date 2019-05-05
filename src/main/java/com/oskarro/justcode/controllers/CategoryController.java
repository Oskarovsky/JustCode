package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleServiceImpl;
import com.oskarro.justcode.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "all_categories_of_article";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add_category";
    }

    @PostMapping("/add")
    public String addCategory(Model model,
                              @Valid Category category, BindingResult br) {
        categoryService.add(category);
        model.addAttribute("category", new Category());
        return "add_category";
    }

    @GetMapping(value = "/adds")
    public String addCategoryToArticle(Model model,
                                       @RequestParam(value = "id", required = true) Long id) {
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.getAll());
        return "add_categories";
    }

    @GetMapping("/relationAdd")
    public String addParameter(Model model,
                               @RequestParam(value = "idArticle", required = true) Long idArticle,
                               @RequestParam(value = "idCategory", required = true) Long idCategory) {
        Category cat = categoryService.findById(idCategory);
        Article art = articleService.findById(idArticle);
        cat.getArticles().add(art);
        categoryService.save(cat);
        model.addAttribute("id", idArticle);
        model.addAttribute("categories", categoryService.getAll());
        return "add_categories";
    }

    @GetMapping("/getArticleCategory")
    public String getAllCategoriesArticle(Model model,
                                          @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("categories", articleService.getAllCategories(id));
        Article article = articleService.findById(id);
        model.addAttribute("articleId", article.getId());
        return "all_categories_of_article";
    }

    @PostMapping("/delete")
    public String deleteParameter(Model model, RedirectAttributes redirectAttributes,
                               @RequestParam(value = "idArticle", required = false) Long idArticle,
                               @RequestParam(value = "idCategory", required = true) Long idCategory) {
        Category cat = categoryService.findById(idCategory);
        Article art = articleService.findById(idArticle);
        cat.getArticles().remove(art);
        categoryService.save(cat);
        redirectAttributes.addAttribute("idArt", idArticle);
        model.addAttribute("id", idArticle);
        model.addAttribute("categories", articleService.getAllCategories(idArticle));
        return "redirect:/category/getArticleCategory?id={idArt}";
    }



}












