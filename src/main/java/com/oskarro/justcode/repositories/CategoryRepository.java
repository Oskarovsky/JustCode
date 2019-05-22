package com.oskarro.justcode.repositories;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);

    @Query("select cat.articles from Category cat where cat.id=?1")
    List<Article> getAllArticles(Long idArticle);

}
