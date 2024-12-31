package com.javaproject.project_backend_spring.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

import com.javaproject.project_backend_spring.entity.post.PostUserEntity;

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
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column()
  private String email;

  @Column()
  private String password;

  @Column(columnDefinition = "varchar(255) default 'email'")
  private String provider;

  @Column()
  private String firstName;

  @Column()
  private String lastName;

  @Column()
  private String imageUrl;

  @Column()
  private Date createAt;

  @Column()
  private Date updateAt;

  @Column()
  private Date deleteAt;

  @Column()
  private boolean isDelete;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostUserEntity> postUsers;
}
