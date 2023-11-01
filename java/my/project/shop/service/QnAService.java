package my.project.shop.service;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import my.project.shop.entity.QnA;
import my.project.shop.entity.Review;

import java.util.List;

@Service
public interface QnAService {
    List<QnA> getAllQnA();
    Page<QnA> getQnAByPage(int page, int size);
    QnA save(QnA qnA);
    
    // QnA 관련 메서드 정의 가능
}
