package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Comment;
import com.oskarro.justcode.repositories.ArticleRepository;
import com.oskarro.justcode.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/article/{articleId}/comments")
    public Page<Comment> getAllCommentsByArticleId(@PathVariable (value = "articleId") Long articleId,
                                                   Pageable pageable) {
        return commentRepository.findByArticleId(articleId, pageable);
    }


}
