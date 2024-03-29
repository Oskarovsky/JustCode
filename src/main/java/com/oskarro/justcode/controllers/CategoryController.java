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
import java.util.List;
import java.util.Optional;

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
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("categories", categoryService.getAll());
        return "general/all_categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("category", new Category());
        return "admin/add_category";
    }

    @PostMapping("/add")
    public String addCategory(Model model,
                              @Valid Category category, BindingResult br) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        categoryService.add(category);
        model.addAttribute("category", new Category());
        return "admin/add_category";
    }

    @GetMapping("/adds")
    public String addCategoryToArticle(Model model,
                                       @RequestParam(value = "id", required = true) Long id) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.getAll());
        return "admin/add_categories";
    }

    @GetMapping("/relationAdd")
    public String addParameter(Model model,
                               @RequestParam(value = "idArticle", required = true) Long idArticle,
                               @RequestParam(value = "idCategory", required = true) Long idCategory) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        Category cat = categoryService.findById(idCategory);
        Optional<Article> art = articleService.findById(idArticle);
        cat.getArticles().add(art.get());
        categoryService.save(cat);
        model.addAttribute("id", idArticle);
        model.addAttribute("categories", categoryService.getAll());
        return "admin/add_categories";
    }

    @GetMapping("/getArticleCategory")
    public String getAllCategoriesArticle(Model model,
                                          @RequestParam(value = "id", required = false) Long id) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("categories", articleService.getAllCategories(id));
        Optional<Article> article = articleService.findById(id);
        model.addAttribute("articleId", article.get().getId());
        return "general/all_categories_of_article";
    }

    @PostMapping("/delete")
    public String deleteParameter(Model model, RedirectAttributes redirectAttributes,
                               @RequestParam(value = "idArticle", required = false) Long idArticle,
                               @RequestParam(value = "idCategory", required = true) Long idCategory) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        Category cat = categoryService.findById(idCategory);
        Optional<Article> art = articleService.findById(idArticle);
        cat.getArticles().remove(art);
        categoryService.save(cat);
        redirectAttributes.addAttribute("idArt", idArticle);
        model.addAttribute("id", idArticle);
        model.addAttribute("categories", articleService.getAllCategories(idArticle));
        return "redirect:/category/getArticleCategory?id={idArt}";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(Model model,
                                 @PathVariable Long id) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        categoryService.deleteCategory(id);
        model.addAttribute("categories", categoryService.getAll());
        return "redirect:/category/all";
    }



}












