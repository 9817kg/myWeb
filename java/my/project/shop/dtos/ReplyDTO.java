package my.project.shop.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReplyDTO {
    private Long rno;
    private String replyer;
    private String text;
    
    
    private Long bno; // 제목글 번호
    private LocalDateTime regDate, modDate;
    
}


