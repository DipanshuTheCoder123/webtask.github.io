package com.example.ecommer.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommer.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


    public boolean existsByName( String name);
}
