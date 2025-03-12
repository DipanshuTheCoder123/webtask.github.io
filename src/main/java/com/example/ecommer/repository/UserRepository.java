package com.example.ecommer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.example.ecommer.model.UserDtls;
import java.util.List;


public interface UserRepository extends JpaRepository<UserDtls ,Integer> {
    
    public UserDtls findByEmail(String email);
     
    public UserDtls getUserByEmail(String email);
}
