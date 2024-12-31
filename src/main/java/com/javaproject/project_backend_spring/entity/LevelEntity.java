package com.javaproject.project_backend_spring.entity;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

import com.javaproject.project_backend_spring.entity.post.PostLevelEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "level")
public class LevelEntity {
  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column()
  private String label;

  @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostLevelEntity> postLevels;
}
