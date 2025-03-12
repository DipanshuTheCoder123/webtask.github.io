package com.example.ecommer.serviceimp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommer.model.Category;
import com.example.ecommer.repository.CategoryRepository;
import com.example.ecommer.service.CategoryService;
@Service
public class CategoryServiceimpl  implements CategoryService{
      @Autowired
    private CategoryRepository categoryRepository;
    private Category orElse;

     
    @Override
    public Category saveCategory(Category category) {
        // TODO Auto-generated method stub
        return categoryRepository.save(category);

    }


    @Override
    public List<Category> getAllCategory() {
        // TODO Auto-generated method stub
       return categoryRepository.findAll();
    }


    @Override
    public Boolean existCategory(String name) {
        // TODO Auto-generated method stub
       return categoryRepository.existsByName(name);
    }


    @Override
    public boolean deletecategory( int id) {
       
        if (categoryRepository.existsById(id)) { // Check if category exists
            categoryRepository.deleteById(id); // Delete the category
            return true; // Successful deletion
        }
        return false; 
    }


    @Override
    public Category getCategoryById(int id) {
          Category category = categoryRepository.findById(id).orElse(null);
      
          
          return category;

    }


    @Override
    public List<Category> getAllCategories() {
        
        return categoryRepository.findAll();
          } 


    

    
    


}
