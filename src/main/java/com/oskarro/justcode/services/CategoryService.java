package com.oskarro.justcode.services;

import com.oskarro.justcode.domains.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAll();

    public void add(Category category);

    public Category findById(Long id);

    public void save(Category category);
}
