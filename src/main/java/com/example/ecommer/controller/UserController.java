package com.example.ecommer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommer.model.Cart;
import com.example.ecommer.service.CartService;



@Controller

public class UserController {

   @Autowired
   public CartService cartService;


    @GetMapping("/user/home")
    public String  home(){
  return "user/Home"; 
    }
   
    @GetMapping("/user/addCart")
    public String addToCart(@RequestParam("pid") Integer productId, @RequestParam("uid") Integer  userId) {
       
        Cart saveCart = cartService.saveCart(productId, userId);

        return "redirect:/product/"+productId ;
    }
    
    
}
