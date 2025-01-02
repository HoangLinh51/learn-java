package com.javaproject.project_backend_spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.project_backend_spring.dto.PostDto;
import com.javaproject.project_backend_spring.entity.UserEntity;
import com.javaproject.project_backend_spring.entity.post.PostEntity;
import com.javaproject.project_backend_spring.entity.post.PostLevelEntity;
import com.javaproject.project_backend_spring.entity.post.PostUserEntity;
import com.javaproject.project_backend_spring.repository.PostRepository;
import com.javaproject.project_backend_spring.repository.UserRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  public PostEntity createPost(PostDto dataPostDto) {
    System.out.println("PostDto: " + dataPostDto);
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      SimpleDateFormat formatterFull = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

      PostEntity postEntity = new PostEntity();
      postEntity.setCourt(dataPostDto.getCourt());
      postEntity.setDate(formatter.parse(dataPostDto.getDate()));
      postEntity.setStartTime(formatterFull.parse(dataPostDto.getStartTime()));
      postEntity.setEndTime(formatterFull.parse(dataPostDto.getEndTime()));
      postEntity.setSlot(dataPostDto.getSlot());
      postEntity.setNumberOfCourt(dataPostDto.getNumberOfCourt());
      postEntity.setPrice(dataPostDto.getPrice());
      postEntity.setDelete(false);
      postEntity.setCreateAt(new Date());
      postEntity.setUpdateAt(new Date());

      // Handle PostLevelEntity
      List<PostLevelEntity> postLevelEntities = dataPostDto.getLevel().parallelStream().map(level -> {
        PostLevelEntity postLevelEntity = new PostLevelEntity();
        postLevelEntity.setLevel(level);
        postLevelEntity.setPost(postEntity);
        postLevelEntity.setCreateAt(new Date());
        postLevelEntity.setUpdateAt(new Date());
        return postLevelEntity;
      }).collect(Collectors.toList());
      postEntity.setPostLevels(postLevelEntities);

      // Handle PostUserEntity
      UserEntity hostUser = userRepository.findById(dataPostDto.getHostId())
          .orElseThrow(() -> new RuntimeException("User not found with ID: " + dataPostDto.getHostId()));
      PostUserEntity postUserEntity = new PostUserEntity();
      postUserEntity.setUser(hostUser);
      postUserEntity.setPost(postEntity);
      postUserEntity.setHost(true);
      postUserEntity.setCreateAt(new Date());
      postUserEntity.setUpdateAt(new Date());
      postEntity.setPostUsers(List.of(postUserEntity));

      return postRepository.save(postEntity);
    } catch (Exception e) {
      System.err.println("Error creating post: " + e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("Error creating post", e);
    }
  }
}