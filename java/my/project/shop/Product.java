package my.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemcount;
    private String category;

    private String img;
    private String provider;
    private String title;
    private String date;
    private String count;
    private String price;
    private String description;

}