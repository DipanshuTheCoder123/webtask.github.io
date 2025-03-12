package com.example.ecommer.service;

import java.util.List;

import com.example.ecommer.model.Category;


public interface CategoryService {

    public Category saveCategory(Category category);

    public Boolean existCategory(String name);

    public List<Category> getAllCategory();

    public boolean deletecategory(int id);

    public Category getCategoryById(int id);

    public List<Category> getAllCategories();
}