package my.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import my.project.shop.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // Notice 엔티티에 대한 특별한 쿼리 메서드를 선언할 수 있음
}
