package my.project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import my.project.shop.entity.Inquiry;
import my.project.shop.repository.InquiryRepository;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {
	private final InquiryRepository inquiryRepository;

	@Autowired
	public InquiryServiceImpl(InquiryRepository inquiryRepository) {
		this.inquiryRepository = inquiryRepository;
	}

	@Override
	public List<Inquiry> getAllNotices() {
		return inquiryRepository.findAll();
	}

	@Override
	public Page<Inquiry> getInquiryByPage(int page, int size) {
		return inquiryRepository.findAll(PageRequest.of(page - 1, size));
	}

	// Notice 관련 메서드 구현 가능
}
