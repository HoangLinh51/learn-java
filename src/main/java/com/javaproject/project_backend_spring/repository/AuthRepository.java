package com.javaproject.project_backend_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.project_backend_spring.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, String> {
  boolean existsByEmail(String email);
  UserEntity findByEmail(String email);
}