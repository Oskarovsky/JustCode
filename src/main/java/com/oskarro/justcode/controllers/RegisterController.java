package com.oskarro.justcode.controllers;

import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.domains.UserRegistrationDto;
import com.oskarro.justcode.services.ArticleService;
import com.oskarro.justcode.services.CategoryService;
import com.oskarro.justcode.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;
    ArticleService articleService;
    CategoryService categoryService;

    public RegisterController(ArticleService articleService, CategoryService categoryService,
                              UserService userService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<Category> allCategories = categoryService.getAll();
        model.addAttribute("allCategories", allCategories);
        return "general/register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {
        Optional<User> existing = userService.findByEmail(userDto.getEmail());
        if (existing.isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "general/register";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }
}
