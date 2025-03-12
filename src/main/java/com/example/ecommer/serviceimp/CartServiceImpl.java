package com.example.ecommer.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.ecommer.model.Cart;
import com.example.ecommer.model.Product;
import com.example.ecommer.model.UserDtls;
import com.example.ecommer.repository.CartRepository;
import com.example.ecommer.repository.ProductRepository;
import com.example.ecommer.repository.UserRepository;
import com.example.ecommer.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Cart saveCart(Integer productId, Integer UserId) {
      UserDtls userDtls = userRepository.findById(UserId).get();
		Product product = productRepository.findById(productId).get();

		Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, UserId);

		Cart cart = null;

		if (ObjectUtils.isEmpty(cartStatus)) {
			cart = new Cart();
			cart.setProduct(product);
			cart.setUser(userDtls);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		} else {
			cart = cartStatus;
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		Cart saveCart = cartRepository.save(cart);

		return saveCart;


    }

    @Override
    public List<Cart> getCartsByUser(Integer userId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getCartsByUser'");
    }

    @Override
    public Integer getCountCart(Integer userId) {
     
        Integer countByUserId = cartRepository.countByUserId(userId); 
        return countByUserId;
    }

    @Override
    public void updateQuantity(String sy, Integer cid) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateQuantity'");
    }

    // @Override
    // public List<Cart> getCartByUser(Integer userid) {

    // }

}
