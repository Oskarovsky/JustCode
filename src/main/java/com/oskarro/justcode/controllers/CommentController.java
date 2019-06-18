package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Comment;
import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.repositories.ArticleRepository;
import com.oskarro.justcode.repositories.CommentRepository;
import com.oskarro.justcode.services.ArticleService;
import com.oskarro.justcode.services.ArticleServiceImpl;
import com.oskarro.justcode.services.CommentService;
import com.oskarro.justcode.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;
    ArticleService articleService;
    CommentService commentService;
    UserService userService;

    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository,
                             ArticleService articleService, CommentService commentService,
                             UserService userService) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/createComment")
    public String createNewComment(@Valid Comment comment, Model model,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "general/comment_form";
        } else {
            commentService.save(comment);
            return "redirect:/article/show/" + comment.getArticle().getId();
        }
    }


    @PostMapping("/article/{id}/comment")
    public String addComment(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "redirect:/article/show" + id;
    }



}
