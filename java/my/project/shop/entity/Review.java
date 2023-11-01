package my.project.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "product")
public class Review extends BaseEntity{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long rno;
   private String text;
   private String reviewer;
   private String title;
   private String date;
   

   @ManyToOne(fetch = FetchType.EAGER) // 다대일 관계 설정
    private Product product; // Product 엔티티와의 관계

   
   public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setTitle(String title) {
	this.title = title;
    }
   

   public void setProduct(Product product) {
      this.product = product;
   }
   
   public Product getProduct() {
       return product;
   }
   

}