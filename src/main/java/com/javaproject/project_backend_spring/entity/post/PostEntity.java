package com.javaproject.project_backend_spring.entity.post;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

 
import com.javaproject.project_backend_spring.entity.CourtEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "post")
public class PostEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column()
  @Temporal(TemporalType.DATE)
  private Date date;

  @Column()
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;

  @Column()
  @Temporal(TemporalType.TIMESTAMP)
  private Date endTime;

  @Column()
  private int slot;

  @Column()
  private PostStatus status;

  @Column()
  private Integer[] numberOfCourt;

  @Column()
  private double price;

  @Column()
  private boolean isDelete;

  @Column()
  private Date createAt;

  @Column()
  private Date updateAt;

  @ManyToOne
  @JoinColumn(name = "courtId", nullable = false)
  private CourtEntity court;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
  @ToString.Exclude
  private List<PostLevelEntity> postLevels;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
  @ToString.Exclude
  private List<PostUserEntity> postUsers;
}