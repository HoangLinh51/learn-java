package com.javaproject.project_backend_spring.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

import com.javaproject.project_backend_spring.entity.post.PostEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "court")
public class CourtEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column()
  private String name;

  @Column()
  private String address;

  @Column()
  private boolean idDelete;

  @Column()
  private Date createAt;

  @Column()
  private Date updateAt;

  @Column()
  private Date deleteAt;

  @OneToMany(mappedBy = "court", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ToString.Exclude
  private List<PostEntity> posts;
}
