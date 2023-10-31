package my.project.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import my.project.shop.entity.Inquiry;
import my.project.shop.entity.QnA;

import java.util.List;

@Service
public interface InquiryService {
    List<Inquiry> getAllNotices();
    Page<Inquiry> getInquiryByPage(int page, int size);
    // Notice 관련 메서드 정의 가능
}
