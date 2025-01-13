package com.javaproject.project_backend_spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.project_backend_spring.dto.request.PostDto;
import com.javaproject.project_backend_spring.dto.response.LevelDto;
import com.javaproject.project_backend_spring.dto.response.PostResponseDto;
import com.javaproject.project_backend_spring.entity.UserEntity;
import com.javaproject.project_backend_spring.entity.post.PostEntity;
import com.javaproject.project_backend_spring.entity.post.PostLevelEntity;
import com.javaproject.project_backend_spring.entity.post.PostStatus;
import com.javaproject.project_backend_spring.entity.post.PostUserEntity;
import com.javaproject.project_backend_spring.repository.PostRepository;
import com.javaproject.project_backend_spring.repository.UserRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;
  
  public List<PostResponseDto> getAllPosts() {
    return postRepository.findAll().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  public Optional<PostResponseDto> getPostById(String postId) {
    return postRepository.findById(postId).map(this::convertToDto);
  }

  private PostResponseDto convertToDto(PostEntity postEntity) {
    PostResponseDto dto = new PostResponseDto();
    dto.setId(postEntity.getId());
    dto.setCourtName(postEntity.getCourt().getName());
    dto.setCourtAddress(postEntity.getCourt().getAddress());
    dto.setDate(postEntity.getDate());
    dto.setStartTime(postEntity.getStartTime());
    dto.setEndTime(postEntity.getEndTime());
    dto.setNumberOfCourt(postEntity.getNumberOfCourt());
    dto.setSlot(postEntity.getSlot());
    dto.setPrice(postEntity.getPrice());
    dto.setLevel( postEntity.getPostLevels().parallelStream().map(level -> {
      LevelDto levelDto = new LevelDto();
      levelDto.setId(level.getLevel().getId());
      levelDto.setLabel(level.getLevel().getLabel());
      return levelDto;
    }).collect(Collectors.toList()));
    dto.setStatus(postEntity.getStatus());
    dto.setDelete(postEntity.isDelete());
    return dto;
  }

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
      postEntity.setStatus(PostStatus.ACTIVE);
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