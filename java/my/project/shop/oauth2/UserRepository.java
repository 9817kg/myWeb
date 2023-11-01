package my.project.shop.oauth2;


import org.springframework.data.jpa.repository.JpaRepository;

import my.project.shop.entity.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findUserByEmailAndProvider(String email, String provider); 
}