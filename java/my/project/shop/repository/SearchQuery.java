package my.project.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.project.shop.entity.Member;

public interface SearchQuery extends JpaRepository<Member, String> {
    // 검색 쿼리
    @Query(value = "SELECT category FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productCategoryWithTitle(@Param("search") String search);
    @Query(value = "SELECT description FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productDescriptionWithTitle(@Param("search") String search);
    @Query(value = "SELECT img FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productImageWithTitle(@Param("search") String search);
    @Query(value = "SELECT price FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productPriceWithTitle(@Param("search") String search);
    @Query(value = "SELECT provider FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productProviderWithTitle(@Param("search") String search);
    @Query(value = "SELECT title FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productTitleWithTitle(@Param("search") String search);
    @Query(value = "SELECT date FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> productDateWithTitle(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Product p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<Long> productcountWithTitle(@Param("search") String search);
    
    
    @Query(value = "SELECT category FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productCategoryWithcategory(@Param("search") String search);
    @Query(value = "SELECT description FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productDescriptionWithcategory(@Param("search") String search);
    @Query(value = "SELECT img FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productImageWithcategory(@Param("search") String search);
    @Query(value = "SELECT price FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productPriceWithcategory(@Param("search") String search);
    @Query(value = "SELECT provider FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productProviderWithcategory(@Param("search") String search);
    @Query(value = "SELECT title FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productTitleWithcategory(@Param("search") String search);
    @Query(value = "SELECT date FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> productDateWithcategory(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Product p WHERE p.category LIKE %:search%", nativeQuery = true)
     List<Long> productcountWithcategory(@Param("search") String search);
    
    @Query(value = "SELECT category FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productCategoryWithprovider(@Param("search") String search);
    @Query(value = "SELECT description FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productDescriptionWithprovider(@Param("search") String search);
    @Query(value = "SELECT img FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productImageWithprovider(@Param("search") String search);
    @Query(value = "SELECT price FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productPriceWithprovider(@Param("search") String search);
    @Query(value = "SELECT provider FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productProviderWithprovider(@Param("search") String search);
    @Query(value = "SELECT title FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productTitleWithprovider(@Param("search") String search);
    @Query(value = "SELECT date FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<String> productDateWithprovider(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Product p WHERE p.provider LIKE %:search%", nativeQuery = true)
    List<Long> productcountWithprovider(@Param("search") String search);
   
}
