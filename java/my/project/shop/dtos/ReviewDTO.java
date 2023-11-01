package my.project.shop.dtos;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.project.shop.entity.Product;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDTO {

   private Long rno;
   private String reviewer;
   private String text;
   private String password;
   private String content;
   
   private Product product;
   
   private Long itemcount;
   private LocalDateTime regDate, modDate;
}
