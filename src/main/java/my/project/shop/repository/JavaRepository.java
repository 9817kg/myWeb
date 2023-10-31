package my.project.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.project.shop.entity.JavaEntity;
import my.project.shop.entity.Product;



public interface JavaRepository extends JpaRepository<Product, Long>{
    Optional<Product> findBytitle(String provider);
    
     Optional<Product> findByitemcount(Long id);
     
     List<Product> findByCategory(String category);
}


