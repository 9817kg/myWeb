package my.project.shop.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import my.project.shop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategory(String category);

    List<Product> findByCategory(String item);
}
