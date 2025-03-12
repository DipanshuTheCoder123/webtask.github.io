package com.example.ecommer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommer.model.Product;

public interface ProductRepository  extends JpaRepository<Product,Integer>{
   
    List<Product> findByIsActiveTrue();
} 
