package my.project.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResultDto {
    String category;
    String description;
    String img;
    String price;
    String provider;
    String title;
    String date;
    Long itemcount; 
         
}
