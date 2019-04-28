package com.oskarro.justcode.bootstrap;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.repositories.ArticleRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ArticleLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        Article firstPost = new Article();
        firstPost.setTitle("My first blog post!");
        firstPost.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation");
        articleRepository.save(firstPost);

        Article secondPost = new Article();
        secondPost.setTitle("Java is amazing");
        secondPost.setContent("No, not really. It has us writing SQL statements. What if you’re just doing a " +
                "prototype? If you");
        articleRepository.save(secondPost);
    }
}
