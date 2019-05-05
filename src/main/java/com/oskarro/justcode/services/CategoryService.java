package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void add(Category category);

    Category findById(Long id);

    void save(Category category);

    void deleteCategory(Long id);

}
