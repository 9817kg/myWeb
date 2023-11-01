package my.project.shop.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long itemcount;
	private String title;
	private int count;
	private String provider;
	private String category;
	private String date;
	private String price;
	private String description;
	private String img;
}
