package my.project.shop.service;

import java.util.List;

import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageRequestDTO;
import my.project.shop.dtos.ReviewPageResultDTO;
import my.project.shop.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ReviewService {
    Page<Review> getReviewsByPage(int page, int size);
	//신규 리뷰 등록
	Long review(ReviewDTO dto);
	List<Review> fetchReviewsForProduct(Long productId);
	
	
	
	//위 Object[] Entity를 DTO로 변환
	default ReviewDTO entityToDTO(Review review) {
		
		ReviewDTO dto = ReviewDTO.builder()
				.rno(review.getRno())
				.reviewer(review.getReviewer())
				.text(review.getText())
				.regDate(review.getRegDate())
				.modDate(review.getModDate())
				.build();
		
		return dto;
	}
	
	//DTO를 Entity로 변환
	default Review dtoToEntity(ReviewDTO dto) {
		
		Review review = Review.builder()
				.rno(dto.getRno())
				.reviewer(dto.getReviewer())
				.text(dto.getText())
				.build();
		return review;
	}
	List<Review> getReviewsByProductId(Long productId);
	
	Review saveReview(Review review);
	ReviewPageResultDTO<ReviewDTO, Review> getList(ReviewPageRequestDTO pageRequestDTO);
	
	    

}
