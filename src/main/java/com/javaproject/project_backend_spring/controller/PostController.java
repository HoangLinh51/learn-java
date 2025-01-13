package com.javaproject.project_backend_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.project_backend_spring.dto.request.PostDto;
import com.javaproject.project_backend_spring.dto.response.PostResponseDto;
import com.javaproject.project_backend_spring.entity.post.PostEntity;
import com.javaproject.project_backend_spring.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  private PostService postService;

  // @GetMapping("")
  // public String api() {
  //   return "API get is working";
  // }

  @GetMapping("")
  public ResponseEntity<List<PostResponseDto>> getAllPosts() {
    List<PostResponseDto> posts = postService.getAllPosts();
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<?> getPostById(@PathVariable String postId) {
    Optional<PostResponseDto> postDto = postService.getPostById(postId);
    if (postDto.isPresent()) {
      return ResponseEntity.ok(postDto.get());
    } else {
      return ResponseEntity.status(404).body("Post not found with ID: " + postId);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> createPost(@RequestBody PostDto dataPostDto) {
    try {
      PostEntity createdPost = postService.createPost(dataPostDto);
      return ResponseEntity.ok(createdPost);
    } catch (RuntimeException e) {
      return ResponseEntity.status(500).body("Error creating post: " + e.getMessage());
    }
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
    return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
  }
}