package com.example.ecommer.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommer.model.Cart;
import com.example.ecommer.model.Category;
import com.example.ecommer.model.Product;
import com.example.ecommer.model.UserDtls;
import com.example.ecommer.service.CartService;
import com.example.ecommer.service.CategoryService;
import com.example.ecommer.service.ProductService;
import com.example.ecommer.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/base")
    public String base() {
        return "base";
    } @ModelAttribute
    public void getUserDetails(Principal p, Model m) {
        List<Category> allCategories = categoryService.getAllCategories();
        m.addAttribute("category", allCategories);
        if (p != null) {
            String email = p.getName();
            UserDtls userByEmail = userService.getUserByEmail(email); // Correct method
            m.addAttribute("user", userByEmail);
            Integer countCart =cartService.getCountCart(userByEmail.getId()); 
            m.addAttribute("cout", countCart);
             			
        } else {
            System.out.println("User is not logged in"); // Debugging
        }
    }
    

   

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/sub")
    public String submitform() {
        return "sumbitform";
    }

    @GetMapping("/product")
    public String product(Model m) {
        m.addAttribute("product", productService.getAllActiveproduct());

        return "Product";
    }

    @GetMapping("/product/{id}")
    public String getViewDetails(@PathVariable("id") int id, Model m) {
        System.out.println(productService.getProductByid(id));
        m.addAttribute("p1", productService.getProductByid(id));
        return "ViewDetails";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user) {

        // Save the user entity to the database
        userService.saveUser(user);

        // Optionally add a success message

        return "redirect:/register";
    }
}
