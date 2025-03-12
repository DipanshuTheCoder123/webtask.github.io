package com.example.ecommer.serviceimp;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommer.model.UserDtls;
import com.example.ecommer.repository.CategoryRepository;
import com.example.ecommer.repository.UserRepository;
import com.example.ecommer.service.UserService;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public UserDtls saveUser(UserDtls user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole("ROLE_USER");
        return userRepository.save(user);

    }

    @Override
    public UserDtls getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);

           }

   
}
