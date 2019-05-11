package com.oskarro.justcode.bootstrap;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.domains.Role;
import com.oskarro.justcode.domains.User;
import com.oskarro.justcode.repositories.ArticleRepository;
import com.oskarro.justcode.repositories.CategoryRepository;
import com.oskarro.justcode.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class ArticleLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ArticleLoader(ArticleRepository articleRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        articleRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        articleRepository.saveAll(getArticles());
        userRepository.saveAll(getUsers());
    }

    private List<Article> getArticles() {

        List<Article> articles = new ArrayList<>();

        Category javaCategory = new Category("Java");
        Category springCategory = new Category("Spring");

        Article firstPost = new Article();
        firstPost.setTitle("My first blog post!");
        firstPost.setDescription("This is my first post on this web.");
        firstPost.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation");
        firstPost.addCategory(javaCategory);
        articleRepository.save(firstPost);
        articles.add(firstPost);

        Article secondPost = new Article();
        secondPost.setTitle("Java is amazing");
        secondPost.setDescription("We want to create new blog website for java fanatics");
        secondPost.setContent("No, not really. It has us writing SQL statements. What if you’re just doing a " +
                "prototype? If you");
        secondPost.addCategory(springCategory);
        articleRepository.save(secondPost);
        articles.add(secondPost);

        return articles;
    }

    String password = "oskar";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);

    private List<User> getUsers() {

        List<User> users = new ArrayList<>();

        List<Role> adminRole = Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER"));
        List<Role> userRole = Arrays.asList(new Role("ROLE_USER"));

        User adminUser = new User();
        adminUser.setFirstName("Oskar");
        adminUser.setLastName("Slyk");
        adminUser.setPassword(hashedPassword);
        adminUser.setEmail("oskar.slyk@gmail.com");
        adminUser.setRoles(adminRole);
        userRepository.save(adminUser);
        users.add(adminUser);

        User userUser = new User();
        userUser.setFirstName("Jacek");
        userUser.setLastName("Placek");
        userUser.setPassword(hashedPassword);
        userUser.setEmail("oski@wp.pl");
        userUser.setRoles(userRole);
        userRepository.save(userUser);
        users.add(userUser);

        return users;
    }
}
