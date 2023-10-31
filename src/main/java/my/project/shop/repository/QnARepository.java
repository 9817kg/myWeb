package my.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import my.project.shop.entity.QnA;

public interface QnARepository extends JpaRepository<QnA, Long> {
    
}
