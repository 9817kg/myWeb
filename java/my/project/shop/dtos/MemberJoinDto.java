package my.project.shop.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String memberType;
    private String introduce;
    private String career;
    private String Provider;
    private String phone;
    private String birth; // 수정: 생년
    private String role;
    private String profile;
}
