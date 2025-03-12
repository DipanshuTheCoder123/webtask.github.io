package com.example.ecommer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommer.model.Cart;
import com.example.ecommer.model.Product;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart,Integer> {


    public Cart findByProductIdAndUserId(Integer productId, Integer userId);


	public Integer countByUserId(Integer userId);

	public List<Cart> findByUserId(Integer userId);
} 