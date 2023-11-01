package my.project.shop.service;

import org.springframework.stereotype.Service;
import my.project.shop.entity.Notice;

import java.util.List;

@Service
public interface NoticeService {
    List<Notice> getAllNotices();

    // Notice 관련 메서드 정의 가능
}
