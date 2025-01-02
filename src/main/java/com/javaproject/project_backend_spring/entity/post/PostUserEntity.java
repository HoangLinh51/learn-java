package com.javaproject.project_backend_spring.entity.post;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaproject.project_backend_spring.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "postUser")
public class PostUserEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column()
  private boolean isHost;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "postId", nullable = false)
  @JsonIgnore
  private PostEntity post;

  @Column()
  private Date createAt;

  @Column()
  private Date updateAt;
}