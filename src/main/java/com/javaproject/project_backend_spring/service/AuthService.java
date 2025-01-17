package com.javaproject.project_backend_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.project_backend_spring.dto.request.UserDto;
import com.javaproject.project_backend_spring.entity.UserEntity;
import com.javaproject.project_backend_spring.repository.AuthRepository;
@Service
public class AuthService {

  @Autowired
  private AuthRepository authRepository;
  public UserEntity register(UserDto userdata) {
    try {
      if (authRepository.existsByEmail(userdata.getEmail())) {
        throw new RuntimeException("Email invalid");
      }
      UserEntity user = new UserEntity();
      user.setEmail(userdata.getEmail());
      user.setFirstName(userdata.getFirstName());
      user.setLastName(userdata.getLastName());

      if (userdata.getPassword() != null && !userdata.getPassword().isEmpty()) {
        user.setPassword(userdata.getPassword());
      } else {
        user.setPassword(null);
      }

      if (userdata.getImageUrl() != null && !userdata.getImageUrl().isEmpty()) {
        user.setImageUrl(userdata.getImageUrl());
      } else {
        user.setImageUrl(null);
      }

      user.setProvider(userdata.getProvider());
      user.setDelete(false);

      return authRepository.save(user);
    } catch (Exception e) {
      System.err.println("Error creating post: " + e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("Error creating post", e);
    }
  }

  public UserEntity login(String email, String password) {
    UserEntity user = authRepository.findByEmail(email);
    if (user == null || !user.getPassword().equals(password)) {
        throw new RuntimeException("Invalid email or password");
    }
    return user;
}
}
