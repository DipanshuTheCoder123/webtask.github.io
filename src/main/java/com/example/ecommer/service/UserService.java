package com.example.ecommer.service;

import java.util.ArrayList;

import com.example.ecommer.model.UserDtls;

public interface UserService {

   public UserDtls saveUser(UserDtls user);

   public UserDtls getUserByEmail(String email);

  
}