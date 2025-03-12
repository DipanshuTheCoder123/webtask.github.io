package com.example.ecommer.service;

import java.util.List;

import com.example.ecommer.model.Cart;

public interface CartService {
    
     public Cart saveCart(Integer productId,Integer UserId);
      

     
	public List<Cart> getCartsByUser(Integer userId);
	
	public Integer getCountCart(Integer userId);

	public void updateQuantity(String sy, Integer cid);


}
