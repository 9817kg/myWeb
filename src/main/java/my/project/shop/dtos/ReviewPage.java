package my.project.shop.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.project.shop.entity.Review;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPage {
    private List<Review> reviews;
    private int totalPages;
    private long totalReviews;

}
