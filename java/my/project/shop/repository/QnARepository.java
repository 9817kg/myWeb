package my.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.project.shop.entity.QnA;

public interface QnARepository extends JpaRepository<QnA, Long> {
	  @Query("SELECT i.title FROM QnA i WHERE i.id = :no")
	   String selectTitle(@Param("no") Long no);
	   @Query("SELECT i.content FROM QnA i WHERE i.id = :no")
	   String selectContent(@Param("no") Long no);
	   @Query("SELECT i.author FROM QnA i WHERE i.id = :no")
	   String selectAuthor(@Param("no") Long no);
	   @Query("SELECT i.date FROM QnA i WHERE i.id = :no")
	   String selectDate(@Param("no") Long no);
}
