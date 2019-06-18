package com.oskarro.justcode.bootstrap;

import com.oskarro.justcode.domains.*;
import com.oskarro.justcode.repositories.ArticleRepository;
import com.oskarro.justcode.repositories.CategoryRepository;
import com.oskarro.justcode.repositories.CommentRepository;
import com.oskarro.justcode.repositories.UserRepository;
import com.oskarro.justcode.services.ArticleService;
import com.oskarro.justcode.services.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
public class ArticleLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ArticleService articleService;
    private final CategoryService categoryService;

    public ArticleLoader(ArticleRepository articleRepository, CategoryRepository categoryRepository,
                         UserRepository userRepository, CommentRepository commentRepository, ArticleService articleService,
                         CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        articleRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        articleRepository.saveAll(getArticles());
        userRepository.saveAll(getUsers());
    }

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
        adminUser.setUsername("Oskarro");
        userRepository.save(adminUser);
        users.add(adminUser);

        User userUser = new User();
        userUser.setFirstName("Jacek");
        userUser.setLastName("Placek");
        userUser.setPassword(hashedPassword);
        userUser.setEmail("oski@wp.pl");
        userUser.setRoles(userRole);
        userUser.setUsername("mordeczka");
        userRepository.save(userUser);
        users.add(userUser);

        return users;
    }

    private List<Article> getArticles() {

        Comment comment1 = new Comment();
        comment1.setContent("This is nice job !");

        Comment comment2 = new Comment();
        comment2.setContent("Oh my god");


        Article firstPost = new Article();
        firstPost.setTitle("My first blog post!");
        firstPost.setDescription("This is my first post on this web.");
        firstPost.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation");
        firstPost.setCategories(new HashSet<>());
        firstPost.setComments(new ArrayList<>());
        //firstPost.addComment(comment1);
        //firstPost.addComment(comment2);

        Article secondPost = new Article();
        secondPost.setTitle("Java is amazing");
        secondPost.setDescription("We want to create new blog website for java fanatics");
        secondPost.setContent("No, not really. It has us writing SQL statements. What if you’re just doing a " +
                "prototype? If you");
        secondPost.setCategories(new HashSet<>());

        Article thirdPost = new Article();
        thirdPost.setTitle("Third post");
        thirdPost.setDescription("Short description which gives you basic info about view of my project");
        thirdPost.setContent("When the container is within your navbar, its horizontal padding is removed at " +
                "breakpoints lower than your specified .navbar-expand{-sm|-md|-lg|-xl} class. This ensures we’re not " +
                "doubling up on padding unnecessarily on lower viewports when your navbar is collapsed");
        thirdPost.setCategories(new HashSet<>());

        Article fourthPost = new Article();
        fourthPost.setTitle("Fourth post");
        fourthPost.setDescription("Fixed content about everything and about nothing");
        fourthPost.setContent("Use our position utilities to place navbars in non-static positions. Choose from " +
                "fixed to the top, fixed to the bottom, or stickied to the top (scrolls with the page until it " +
                "reaches the top, then stays there).");
        fourthPost.setCategories(new HashSet<>());

        Article fifthPost = new Article();
        fifthPost.setTitle("Never give up!");
        fifthPost.setDescription("We have made many enhancements to the software that will strengthen system security.");
        fifthPost.setContent("Although it’s not required, you can wrap a navbar in a .container to center it on a " +
                "page or add one within to only center the contents of a fixed or static top navbar.");
        fifthPost.setCategories(new HashSet<>());

        Article sixthPost = new Article();
        sixthPost.setTitle("Sunshine diamond");
        sixthPost.setDescription("This is the newest post, i mean");
        sixthPost.setContent("Navbar navigation links build on our .nav options with their own modifier class " +
                "and require the use of toggler classes for proper responsive styling. Navigation in navbars will " +
                "also grow to occupy as much horizontal space as possible to keep your navbar contents securely aligned.");
        sixthPost.setCategories(new HashSet<>());

        Article seventhPost = new Article();
        seventhPost.setTitle("ManyToMany");
        seventhPost.setDescription("Handling SQL database for all types associations");
        seventhPost.setContent("These libraries provide convenient utility methods to initialize collections like " +
                "Set. Since Google Guava is one of the most commonly used here we have an example from it. The Guava " +
                "has convenient methods for mutable and immutable Set objects:");
        seventhPost.setCategories(new HashSet<>());


        Category javaCategory = new Category("Java", new HashSet<>());
        Category springCategory = new Category("Spring", new HashSet<>());
        Category devCategory = new Category("DevOps", new HashSet<>());
        Category frontCategory = new Category("Front-End", new HashSet<>());
        Category pythonCategory = new Category("Python", new HashSet<>());
        Category sqlCategory = new Category("SQL", new HashSet<>());

        categoryService.add(javaCategory);
        categoryService.add(springCategory);
        categoryService.add(devCategory);
        categoryService.add(frontCategory);
        categoryService.add(pythonCategory);
        categoryService.add(sqlCategory);

        javaCategory.getArticles().add(firstPost);
        javaCategory.getArticles().add(fifthPost);
        javaCategory.addArticle(fifthPost);

        springCategory.getArticles().add(fifthPost);

        devCategory.getArticles().add(secondPost);

        frontCategory.getArticles().add(thirdPost);

        pythonCategory.getArticles().add(fourthPost);
        pythonCategory.getArticles().add(sixthPost);

        sqlCategory.getArticles().add(seventhPost);

        categoryRepository.save(javaCategory);
        categoryRepository.save(springCategory);
        categoryRepository.save(devCategory);
        categoryRepository.save(frontCategory);
        categoryRepository.save(pythonCategory);
        categoryRepository.save(sqlCategory);


        return new ArrayList<>();
    }

    String password = "oskar";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);

}
