package com.javaproject.project_backend_spring.dto.request;

import lombok.Data;

@Data
public class UserDto {
  private String email;
  private String firstName;
  private String lastName;
  private String imageUrl;
  private String password;
  private String provider;
}
