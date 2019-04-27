package com.oskarro.justcode.repositories;

import com.oskarro.justcode.domains.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
