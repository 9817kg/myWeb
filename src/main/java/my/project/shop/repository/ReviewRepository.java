package my.project.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.project.shop.entity.Review;





public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.text, r.reviewer FROM Review r WHERE r.rno = :rno")
	List<Object[]> getReviewInfo(@Param("rno") Long rno);
	
	 @Query("SELECT r FROM Review r") // JPQL 쿼리 수정: 엔티티 Review를 반환하도록 변경
	 Page<Review> getReviewPaged(Pageable pageable);
	List<Review> findByProductItemcount(Long productId);
	
}
