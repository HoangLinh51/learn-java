package com.javaproject.project_backend_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.project_backend_spring.entity.post.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, String> {
}