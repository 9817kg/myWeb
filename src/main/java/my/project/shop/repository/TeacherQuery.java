package my.project.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import my.project.shop.entity.Member;
import my.project.shop.entity.Product;
import my.project.shop.entity.Review;

public interface TeacherQuery extends JpaRepository<Member, String> {

  

    // 이전 쿼리 메서드들을 그대로 유지

    @Query("SELECT m.name FROM Member m WHERE m.name = :teacher")
    String selectName(@Param("teacher") String teacher);

    @Query("SELECT m.introduce FROM Member m WHERE m.name = :teacher")
    String selectIntro(@Param("teacher") String teacher);
    @Query("SELECT m.career FROM Member m WHERE m.name = :teacher")
    String selectCareer(@Param("teacher") String teacher);
    @Query("SELECT m.email FROM Member m WHERE m.name = :teacher")
    String selectEmail(@Param("teacher") String teacher);
   
}
