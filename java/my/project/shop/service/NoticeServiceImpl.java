package my.project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import my.project.shop.entity.Notice;
import my.project.shop.repository.NoticeRepository;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // Notice 관련 메서드 구현 가능
}
