package my.project.shop.service;



import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import my.project.shop.entity.Product;
import my.project.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> Category_item_All(String item) {

        return repository.findByCategory(item);

    }

    public Optional<Product> SelectONE(Long id){
        Optional<Product> belt = repository.findById(id);
        //나중에 형 변환
        return belt;

    }
    
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = repository.findById(productId);
        return productOptional.orElse(null); // 해당 ID에 해당하는 제품이 없으면 null을 반환
    }
}
