package my.project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import my.project.shop.entity.Notice;
import my.project.shop.entity.QnA;
import my.project.shop.repository.NoticeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public Notice save(Notice notice) {
		
		return noticeRepository.save(notice);
	}

	@Override
	public Page<Notice> getNoticeByPage(int page, int size) {
		
			 return noticeRepository.findAll(PageRequest.of(page - 1, size));
		}

	@Override
	public Optional<Notice> getFindId(Long id) {
		
		return noticeRepository.findById(id);
	}
	@Override
    public void deleteNoticeById(Long id) {
        noticeRepository.deleteById(id);
    }
	
	

	
}

  

