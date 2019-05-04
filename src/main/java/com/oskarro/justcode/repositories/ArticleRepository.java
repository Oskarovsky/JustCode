package com.oskarro.justcode.repositories;

import com.oskarro.justcode.domains.Article;
import com.oskarro.justcode.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select art.categories from Article art where art.id=?1")
    List<Category> getAllCategories(Long idCategory);


}
