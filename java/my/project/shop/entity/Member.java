package my.project.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    private String memberType;

    private String phone;

    private String birth;
    private String role;
    private String provider;
    @Column(name = "join_date")
    private String joinDate;
    private String introduce; // 추가: 자기소개
    private String career; // 추가: 경력
    private String profile;

    public static Member createMember(String email,
	    String password, 
	    String name,
	    String memberType,
	    String phone,
	    String birth,
	    String introduce,
	    String career,
	    String profile,
	    PasswordEncoder passwordEncoder) {
	
	Member member = new Member();
	member.setEmail(email);
	member.setPassword(passwordEncoder.encode(password));
	member.setName(name);
	member.setMemberType(memberType);
	member.setPhone(phone);
	member.setBirth(birth);
	member.setIntroduce(introduce);
	member.setCareer(career);
	member.setRole("USER");
	member.setProvider("일반회원");
	member.setProfile("profile.png");

	LocalDateTime joinDate = LocalDateTime.now();

	// 날짜와 시간을 원하는 형식으로 포맷합니다.
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formattedDateTime = joinDate.format(formatter);
	member.setJoinDate(formattedDateTime); // 현재 날짜 설정

	return member;
    }

    // 사용자의 이름이나 이메일을 업데이트하는 메소드
    public Member updateUser(String name, String email, String birth, String phone) {
	this.name = name;
	this.email = email;
	this.birth = birth;
	this.phone = phone;

	return this;
    }

    public Member updateUserGoogle(String name, String email) {
	this.name = name;
	this.email = email;

	return this;
    }

}
