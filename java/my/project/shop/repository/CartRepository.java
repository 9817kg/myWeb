package my.project.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import my.project.shop.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMember(Long member);
}
