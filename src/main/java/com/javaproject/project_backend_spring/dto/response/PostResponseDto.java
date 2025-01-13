package com.javaproject.project_backend_spring.dto.response;

import java.util.Date;
import java.util.List;

import com.javaproject.project_backend_spring.entity.post.PostStatus;

import lombok.Data;

@Data
public class PostResponseDto {
  private String id;
  private String courtName;
  private String courtAddress;
  private Date date;
  private Date startTime;
  private Date endTime;
  private Integer[] numberOfCourt;
  private int slot;
  private double price;
  private List<LevelDto> level;
  private PostStatus status;
  private boolean isDelete;
}
