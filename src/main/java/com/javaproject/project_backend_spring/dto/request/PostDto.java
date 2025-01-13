package com.javaproject.project_backend_spring.dto.request;

import java.util.List;

import com.javaproject.project_backend_spring.entity.CourtEntity;
import com.javaproject.project_backend_spring.entity.LevelEntity;

import lombok.Data;

@Data
public class PostDto {
  private CourtEntity court;
  private String date;
  private String startTime;
  private String endTime;
  private List<LevelEntity> level;
  private Integer[] numberOfCourt;
  private double price;
  private int slot;
  private String hostId;
}