package my.project.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import my.project.shop.entity.JavaEntity;
import my.project.shop.entity.Product;
import my.project.shop.repository.JavaRepository;
@Service
@RequiredArgsConstructor
public class JavaService {
    private final JavaRepository javaRepository;

    public List<Product> getAllJavaEntities() {
        return javaRepository.findAll();
    }
    
    public Product getJavaEntityById(Long id) {
        Optional<Product> optionalJavaEntity = javaRepository.findByitemcount(id);
        return optionalJavaEntity.orElse(null);
    }
    public void saveJavaEntity(Product product) {
        javaRepository.save(product);
    }
 // 카테고리로 제품 목록 가져오기
    public List<Product> getProductsByCategory(String category) {
        return javaRepository.findByCategory(category);
    }

}


