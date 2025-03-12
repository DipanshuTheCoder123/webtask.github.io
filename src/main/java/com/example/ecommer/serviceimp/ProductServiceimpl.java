package com.example.ecommer.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommer.model.Category;
import com.example.ecommer.model.Product;
import com.example.ecommer.repository.ProductRepository;
import com.example.ecommer.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllPRoduct() {

        return productRepository.findAll();

    }

    @Override
    public void deleteProductById(int id) {

        productRepository.deleteById(id);
        // TODO Auto-generated method stub
    }

    @Override
    public Product getProductByid(int id) {
        return productRepository.findById(id).orElse(null);
    }
    

    @Override
    public List<Product> getAllActiveproduct() {
        return productRepository.findByIsActiveTrue(); // Using is_Active with the underscore
    }

}
