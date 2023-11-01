package my.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Long itemcount;
    
    private Long member;


}
