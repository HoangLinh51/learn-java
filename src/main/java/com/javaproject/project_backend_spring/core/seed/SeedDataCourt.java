package com.javaproject.project_backend_spring.core.seed;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javaproject.project_backend_spring.entity.CourtEntity;
import com.javaproject.project_backend_spring.repository.CourtRepository;

@Component
public class SeedDataCourt implements CommandLineRunner {

  private final CourtRepository courtRepository;

  public SeedDataCourt(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (courtRepository.count() == 0) {
      CourtEntity court1 = new CourtEntity();
      court1.setName("Sân Bộ Công An");
      court1.setAddress("Đường Nguyễn Văn Cừ (trong khuôn viên Bộ Công An), Quận 1, TP. Hồ Chí Minh");
      court1.setCreateAt(new Date());

      CourtEntity court2 = new CourtEntity();
      court2.setName("Sân Cầu Kho");
      court2.setAddress("Số 2 Nguyễn Văn Cừ, Quận 1, TP. Hồ Chí Minh");
      court2.setCreateAt(new Date());

      CourtEntity court3 = new CourtEntity();
      court3.setName("Sân Hiển Hoa");
      court3.setAddress("262/3 Trần Não, Phường Bình An, Quận 2, TP. Hồ Chí Minh");
      court3.setCreateAt(new Date());

      CourtEntity court4 = new CourtEntity();
      court4.setName("Sân Trần Não");
      court4.setAddress("Đường Trần Não, Quận 2, TP. Hồ Chí Minh");
      court4.setCreateAt(new Date());

      CourtEntity court5 = new CourtEntity();
      court5.setName("Sân Ga Hoà Hưng");
      court5.setAddress("Số 1 Nguyễn Thông, Phường 9, Quận 3, TP. Hồ Chí Minh");
      court5.setCreateAt(new Date());

      CourtEntity court6 = new CourtEntity();
      court6.setName("Sân Hải Châu");
      court6.setAddress("118 Lê Lợi, Hải Châu, Đà Nẵng");
      court6.setCreateAt(new Date());

      CourtEntity court7 = new CourtEntity();
      court7.setName("Sân Trường Đại Học Thể Dục Thể Thao");
      court7.setAddress("44 Dũng Sĩ Thanh Khê, Thanh Khê, Đà Nẵng");
      court7.setCreateAt(new Date());

      CourtEntity court8 = new CourtEntity();
      court8.setName("Sân Trường Cao Đẳng Lương Thực Thực Phẩm");
      court8.setAddress("101B Lê Hữu Trác, Sơn Trà, Đà Nẵng");
      court8.setCreateAt(new Date());

      CourtEntity court9 = new CourtEntity();
      court9.setName("Sân Kỳ Đồng");
      court9.setAddress("55 Kỳ Đồng, Thanh Khê, Đà Nẵng.");
      court9.setCreateAt(new Date());

      CourtEntity court10 = new CourtEntity();
      court10.setName("Sân số 4 Lê Duẩn");
      court10.setAddress("4 Lê Duẩn, Hải Châu, Đà Nẵng.");
      court10.setCreateAt(new Date());

      courtRepository.save(court1);
      courtRepository.save(court2);
      courtRepository.save(court3);
      courtRepository.save(court4);
      courtRepository.save(court5);
      courtRepository.save(court6);
      courtRepository.save(court7);
      courtRepository.save(court8);
      courtRepository.save(court9);
      courtRepository.save(court10);

      System.out.println("Seed data for CourtEntity inserted.");
    } else {
      System.out.println("CourtEntity table already contains data. No seed data inserted.");
    }
  }
}