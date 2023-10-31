package my.project.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.dtos.QnAReviewDto;
import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageResultDTO;
import my.project.shop.entity.Inquiry;
import my.project.shop.entity.Notice;
import my.project.shop.entity.QnA;
import my.project.shop.entity.Review;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.InquiryRepository;
import my.project.shop.repository.QnARepository;
import my.project.shop.service.InquiryService;
import my.project.shop.service.NoticeService;
import my.project.shop.service.QnAService;
import my.project.shop.service.ReviewService;

@Controller
@RequestMapping("my")
public class ReviewAndQnAController {
    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private QnAService qnaService;
    
    @GetMapping("/qna")
    public String getMainPage(@RequestParam(name = "type", required = false) String type,@RequestParam(name = "page", defaultValue = "1") int page, Model model,HttpSession session) {
    	int pageSize = 6; // 페이지당 리뷰 수
    	
    	Object dtoObject = session.getAttribute("dto");
    	if (dtoObject instanceof MemberJoinDto) {
    	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
    	    model.addAttribute("dto", dto);
    	    if ("inquiry".equals(type)) {
                List<Inquiry> inquiryList = inquiryService.getAllNotices();
                Page<Inquiry> inquiryPage =inquiryService.getInquiryByPage(page, pageSize);
                model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
            	model.addAttribute("Page", inquiryPage);
                
                model.addAttribute("data", inquiryList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            } else if ("qna".equals(type)) {
                List<QnA> qnaList = qnaService.getAllQnA();
                Page<QnA> qnaPage =qnaService.getQnAByPage(page, pageSize);
                model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
            	model.addAttribute("Page", qnaPage);
                model.addAttribute("data", qnaList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            }
    	    return "collection";

    	} else if (dtoObject instanceof UserProfile) {
    	    UserProfile userProfile = (UserProfile) dtoObject;
    	    model.addAttribute("dto", userProfile);
    	    if ("inquiry".equals(type)) {
    	    	 List<Inquiry> inquiryList = inquiryService.getAllNotices();
    	    	 Page<Inquiry> inquiryPage =inquiryService.getInquiryByPage(page, pageSize);
                 model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
             	model.addAttribute("Page", inquiryPage);
                model.addAttribute("data", inquiryList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            } else if ("qna".equals(type)) {
                List<QnA> qnaList = qnaService.getAllQnA();
                Page<QnA> qnaPage =qnaService.getQnAByPage(page, pageSize);
                model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
            	model.addAttribute("Page", qnaPage);
                model.addAttribute("data", qnaList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            }
    	    return "collection"; // online-store.html 페이지로 이동
    	}else {
    		if ("inquiry".equals(type)) {
    			 List<Inquiry> inquiryList = inquiryService.getAllNotices();
    			 Page<Inquiry> inquiryPage =inquiryService.getInquiryByPage(page, pageSize);
                 model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
             	model.addAttribute("Page", inquiryPage);
                model.addAttribute("data", inquiryList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            } else if ("qna".equals(type)) {
                List<QnA> qnaList = qnaService.getAllQnA();
                Page<QnA> qnaPage =qnaService.getQnAByPage(page, pageSize);
                model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
            	model.addAttribute("Page", qnaPage);
                model.addAttribute("data", qnaList);
                model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
            }
    	}
		return "qna";
    		
        

        
        
    }
}

