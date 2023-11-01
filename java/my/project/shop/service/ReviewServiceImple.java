package my.project.shop.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageRequestDTO;
import my.project.shop.dtos.ReviewPageResultDTO;
import my.project.shop.entity.Review;
import my.project.shop.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImple implements ReviewService {

    private final ReviewRepository reviewRepository;
    
    @Override
    public ReviewPageResultDTO<ReviewDTO, Review> getList(ReviewPageRequestDTO pageRequestDTO) {
        // Page<Review> 타입으로 반환하도록 수정
        Page<Review> result = reviewRepository.getReviewPaged(pageRequestDTO.getPageable(Sort.by("rno").descending()));
        return new ReviewPageResultDTO<>(result, this::entityToDTO);
    }

    @Override
    public Long review(ReviewDTO dto) {
	Review review = dtoToEntity(dto);
	reviewRepository.save(review);
	return review.getRno();
    }

   
    @Override
    public List<Review> fetchReviewsForProduct(Long productId) {
	return getReviewsByProductId(productId);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
	List<Review> reviews = reviewRepository.findByProductItemcount(productId);

	return reviews;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Page<Review> getReviewsByPage(int page, int size) {
	 return reviewRepository.findAll(PageRequest.of(page - 1, size));
    
    }
    

   
    
}
