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

public interface MemberQuery extends JpaRepository<Member, String> {

    @Query("SELECT m.email FROM Member m WHERE m.email = :insertEmail")
    String selectEmail(@Param("insertEmail") String insertEmail);

    // 이전 쿼리 메서드들을 그대로 유지

    @Query("SELECT p.itemcount FROM Product p WHERE p.itemcount = :productId")
    Long selectItemCount(@Param("productId") Long productId);

    @Query("SELECT p.description FROM Product p WHERE p.itemcount = :productId")
    String selectDescription(@Param("productId") Long productId);

    @Query("SELECT p.img FROM Product p WHERE p.itemcount = :productId")
    String selectImg(@Param("productId") Long productId);

    @Query("SELECT p.price FROM Product p WHERE p.itemcount = :productId")
    String selectPrice(@Param("productId") Long productId);

    @Query("SELECT p.provider FROM Product p WHERE p.itemcount = :productId")
    String selectProvider(@Param("productId") Long productId);

    @Query("SELECT p.date FROM Product p WHERE p.itemcount = :productId")
    String selectDate(@Param("productId") Long productId);

    @Query("SELECT p.title FROM Product p WHERE p.itemcount = :productId")
    String selectTitle(@Param("productId") Long productId);

    @Query("SELECT p.category FROM Product p WHERE p.itemcount = :productId")
    String selectCategory(@Param("productId") Long productId);

    @Query("SELECT m.name FROM Member m WHERE m.name = :insertName")
    String selectNameByName(@Param("insertName") String insertName);

    @Query("SELECT m.phone FROM Member m WHERE m.phone= :insertMobile")
    String selectMobile(@Param("insertMobile") String insertMobile);

    @Query("SELECT m.email FROM Member m WHERE m.phone= :insertMobile")
    String selectMobilebyEmail(@Param("insertMobile") String insertMobile);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.password = :pw ")
    void updatePassword(@Param("newPassword") String newPassword, @Param("pw") String pw);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.profile = :newprofile, m.email = :newEmail, m.name = :newName, m.birth = :newBirth WHERE m.email = :email")
    void updateProfile(@Param("newprofile") String newprofile, @Param("newEmail") String newEmail,
	    @Param("newName") String newName, @Param("newBirth") String newBirth, @Param("email") String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.profile = :newprofile, m.email = :newEmail, m.name = :newName, m.introduce = :newIntro, m.career = :newCareer WHERE m.email = :email")
    void updateProfileTeacher(@Param("newprofile") String newprofile, @Param("newEmail") String newEmail,
	    @Param("newName") String newName, @Param("newIntro") String newIntro, @Param("newCareer") String newCareer, @Param("email") String email);


    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.email = :newEmail ")
    void updateUserPassword(@Param("newEmail") String newEmail, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.phone = :newMobile WHERE m.email = :Email ")
    void updateMobileByMobile(@Param("newMobile") String newMobile, @Param("Email") String Email);

    // 회원탈퇴
    @Transactional
    @Modifying
    @Query("DELETE FROM Member WHERE email = :email AND password = :pw")
    void remove(@Param("pw") String pw, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Member WHERE email = :email")
    void remove2(@Param("email") String email);

    // 전화번호 변경
    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.phone = :newMobile ")
    void updateMobile(@Param("newMobile") String newMobile);

    // db 땡겨오기
    @Query("SELECT m.career FROM Member m WHERE m.email = :email ")
    String selectCareer(@Param("email") String email);
    @Query("SELECT m.introduce FROM Member m WHERE m.email = :email ")
    String selectIntro(@Param("email") String email);
    @Query("SELECT m.phone FROM Member m WHERE m.email = :email ")
    String selectPh(@Param("email") String email);
    @Query("SELECT m.memberType FROM Member m WHERE m.email = :email ")
    String selectType(@Param("email") String email);

    @Query("SELECT m.profile FROM Member m WHERE m.email = :email ")
    String selectProfile(@Param("email") String email);

    @Query("SELECT m.name FROM Member m WHERE m.email = :email")
    String selectName(@Param("email") String email);

    @Query("SELECT m.birth FROM Member m WHERE m.email = :email ")
    String selectBirth(@Param("email") String email);

    @Query("SELECT m.password FROM Member m WHERE m.email = :email")
    String selectPw(@Param("email") String email);

    @Query("SELECT m.memberType FROM Member m WHERE m.email = :email")
    String selectMemberType(@Param("email") String email);

    @Query("SELECT m.role FROM Member m WHERE m.email = :email ")
    String selectRole(@Param("email") String email);

    @Query("SELECT m.provider FROM Member m WHERE m.email = :email ")
    String selectProvider(@Param("email") String email);

    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    long selectId(@Param("email") String email);
}
