package com.oskarro.justcode.bootstrap;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import com.oskarro.justcode.repositories.ArticleRepository;
import com.oskarro.justcode.repositories.CategoryRepository;
import lombok.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Component
public class ArticleLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleLoader(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        articleRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        articleRepository.saveAll(getArticles());
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
        firstPost.getCategories().add(javaCategory);
        articleRepository.save(firstPost);
        articles.add(firstPost);

        Article secondPost = new Article();
        secondPost.setTitle("Java is amazing");
        secondPost.setDescription("We want to create new blog website for java fanatics");
        secondPost.setContent("No, not really. It has us writing SQL statements. What if you’re just doing a " +
                "prototype? If you");
        secondPost.getCategories().add(springCategory);
        articleRepository.save(secondPost);
        articles.add(secondPost);


        return articles;
    }
}
