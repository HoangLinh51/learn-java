package com.javaproject.project_backend_spring.core.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javaproject.project_backend_spring.entity.LevelEntity;
import com.javaproject.project_backend_spring.repository.LevelRepository;

@Component
public class SeedDataLevel implements CommandLineRunner {

  private final LevelRepository levelRepository;

  public SeedDataLevel(LevelRepository levelRepository) {
    this.levelRepository = levelRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (levelRepository.count() == 0) {
      LevelEntity level1 = new LevelEntity();
      level1.setLabel("Người mới");

      LevelEntity level2 = new LevelEntity();
      level2.setLabel("Trung bình");

      LevelEntity level3 = new LevelEntity();
      level3.setLabel("Trung bình khá");

      LevelEntity level4 = new LevelEntity();
      level4.setLabel("Khá");

      LevelEntity level5 = new LevelEntity();
      level5.setLabel("Dân chuyên");

      levelRepository.save(level1);
      levelRepository.save(level2);
      levelRepository.save(level3);
      levelRepository.save(level4);
      levelRepository.save(level5);

      System.out.println("Seed data for LevelEntity inserted.");
    } else {
      System.out.println("LevelEntity table already contains data. No seed data inserted.");
    }
  }
}