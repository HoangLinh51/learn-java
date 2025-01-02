package com.javaproject.project_backend_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaproject.project_backend_spring.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}