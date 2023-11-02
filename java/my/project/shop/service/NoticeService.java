package my.project.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import my.project.shop.entity.Notice;
import my.project.shop.entity.QnA;

import java.util.List;
import java.util.Optional;

@Service
public interface NoticeService {
    List<Notice> getAllNotices();
    Page<Notice> getNoticeByPage(int page, int size);
    Notice save(Notice notice);
    void deleteNoticeById(Long id);
    Optional<Notice>getFindId(Long id);
    
    
}
