package com.javaproject.project_backend_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.project_backend_spring.dto.request.UserDto;
import com.javaproject.project_backend_spring.entity.UserEntity;
import com.javaproject.project_backend_spring.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserDto user) {
    authService.register(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<UserEntity> login(@RequestBody UserDto user) {
      try {
          UserEntity userRes = authService.login(user.getEmail(), user.getPassword());
          return ResponseEntity.ok(userRes);
      } catch (RuntimeException e) {
          return ResponseEntity.status(401).body(null);
      }
  }
}
