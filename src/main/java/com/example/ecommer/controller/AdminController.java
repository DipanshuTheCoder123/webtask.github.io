package com.example.ecommer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecommer.model.Category;
import com.example.ecommer.model.Product;
import com.example.ecommer.model.UserDtls;
import com.example.ecommer.service.CategoryService;
import com.example.ecommer.service.ProductService;
import com.example.ecommer.service.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
  @Autowired
    private UserService userService;

    @GetMapping("/admin/addProduct")
    public String addCategory(Model model) {
        model.addAttribute("value", categoryService.getAllCategory());
        model.addAttribute("product", productService.getAllPRoduct());
        return "admin/addProduct";
    }
   
    @ModelAttribute
    public void getUserDetails(Principal p, Model m) {
        List<Category> allCategories = categoryService.getAllCategories();
        m.addAttribute("category", allCategories);
        if (p != null) {
            String email = p.getName();
            UserDtls userByEmail = userService.getUserByEmail(email); // Correct method
            m.addAttribute("user", userByEmail);
        }
    }
    
    @GetMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/admin/category")
    public String category(Model model) {
        model.addAttribute("fetch", categoryService.getAllCategory());
        return "admin/category";
    }

    @PostMapping("/admin/category")
    public String saveCategory(@ModelAttribute("category") Category category) {

        categoryService.saveCategory(category);

        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String deletecategory(@PathVariable("id") int id) {
        categoryService.deletecategory(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/edit/{id}")
    public String EditUserDetails(@PathVariable("id") int id, Model m) {
        m.addAttribute("category", categoryService.getCategoryById(id));
       
        return "admin/edit";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@ModelAttribute Product product) {
        product.setDiscount(0);
        product.setDiscountPrice(product.getPrice());
          productService.saveProduct(product);
        return "redirect:/admin/addProduct";
    }
    @GetMapping("/admin/ViewProduct")
    public String viewProduct(Model model)
    {
        model.addAttribute("product", productService.getAllPRoduct());
        return "admin/ViewProduct";
    }
     
  // Example Spring Boot Controller

   
    @GetMapping("admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
      
            productService.deleteProductById(id); // Assuming a service method exists to delete
            return "redirect:/admin/ViewProduct";
    }

    @GetMapping("/admin/editProduct/{id}")
    public String EditProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductByid(id));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "/admin/editProduct";
    }
      
   @PostMapping("/admin/UpdateProduct")
   public String postMethodName(@ModelAttribute Product product,Model model) {
    if (product.getDiscount() < 0) {
        model.addAttribute("errorMessage", "Discount cannot be less than 0.");
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "/admin/editProduct"; // Return to the same form with error
    }
    if (product.getDiscount() > 0) {
        double discountAmount = (product.getPrice() * product.getDiscount()) / 100;
        product.setDiscountPrice(product.getPrice() - discountAmount);
    } else {
        product.setDiscountPrice(product.getPrice()); // In case there's no discount
    }
    

        // Set the id of the product that is being updated
 
    // Call the service to update the product
    productService.saveProduct(product);

    return "redirect:/admin/ViewProduct";
   }
  
   
    
}
