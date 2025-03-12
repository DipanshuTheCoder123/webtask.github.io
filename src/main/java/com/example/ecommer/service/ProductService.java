package com.example.ecommer.service;

import java.util.List;

import com.example.ecommer.model.Product;

public interface ProductService {

    public Product saveProduct(Product product );

    public List<Product> getAllPRoduct();
    
    public void deleteProductById( int id);
    public Product  getProductByid(int id );
    List<Product>getAllActiveproduct();
} 