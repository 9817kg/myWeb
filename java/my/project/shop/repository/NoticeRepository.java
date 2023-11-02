package my.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import my.project.shop.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
   
}
