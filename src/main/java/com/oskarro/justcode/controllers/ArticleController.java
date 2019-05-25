package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.services.ArticleServiceImpl;
import com.oskarro.justcode.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "general/all_articles";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "admin/add_article";
    }

    @GetMapping("/category/{id}")
    public String listArticlesByCategory(@PathVariable Long id, Model model) {
        model.addAttribute("articles", categoryService.getAllByCategory(id));
        model.addAttribute("category", categoryService.findById(id).getName());
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "/general/all_articles_from_category";
    }


    @PostMapping("/new")
    public String addArticle(Model model,
                             @Valid Article article, BindingResult br,
                             @RequestParam(value = "idCategory", required = true) Long idCategory) {
        articleService.add(article);
        model.addAttribute("article", new Article());
        List<Category> allCategories = categoryService.getAll();
        Category cat = categoryService.findById(idCategory);
        cat.getArticles().add(article);
        categoryService.save(cat);
        model.addAttribute("allCategories", allCategories);
        return "admin/add_article";
    }

    @PostMapping("article")
    public String saveArticle(Article article) {
        articleService.save(article);
        return "redirect:/article/show/" + article.getId();
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allComments", articleService.getAllComments(id));
        model.addAttribute("article", articleService.findById(id));
        return "general/article_show";
    }

    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("article", articleService.findById(id));
        return "admin/add_article";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);
        article.removeArticlesFromCategory();
        articleService.deleteArticle(id);
        return "redirect:/article/all";
    }


}
