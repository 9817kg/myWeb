package my.project.shop.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.dtos.QnAReviewDto;
import my.project.shop.dtos.ReadDTO;
import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageRequestDTO;
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
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private QnARepository qnARepository;

	@GetMapping("/qna")
	public String getMainPage(@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "page", defaultValue = "1") int page, Model model, HttpSession session) {
		int pageSize = 6; // 페이지당 리뷰 수

		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			if ("inquiry".equals(type)) {
				List<Inquiry> inquiryList = inquiryService.getAllNotices();
				Page<Inquiry> inquiryPage = inquiryService.getInquiryByPage(page, pageSize);
				model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
				model.addAttribute("Page", inquiryPage);

				model.addAttribute("data", inquiryList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			} else if ("qna".equals(type)) {
				List<QnA> qnaList = qnaService.getAllQnA();
				Page<QnA> qnaPage = qnaService.getQnAByPage(page, pageSize);
				model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
				model.addAttribute("Page", qnaPage);
				model.addAttribute("data", qnaList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			}
			return "qna";

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			if ("inquiry".equals(type)) {
				List<Inquiry> inquiryList = inquiryService.getAllNotices();
				Page<Inquiry> inquiryPage = inquiryService.getInquiryByPage(page, pageSize);
				model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
				model.addAttribute("Page", inquiryPage);
				model.addAttribute("data", inquiryList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			} else if ("qna".equals(type)) {
				List<QnA> qnaList = qnaService.getAllQnA();
				Page<QnA> qnaPage = qnaService.getQnAByPage(page, pageSize);
				model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
				model.addAttribute("Page", qnaPage);
				model.addAttribute("data", qnaList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			}
			return "qna"; // online-store.html 페이지로 이동
		} else {
			if ("inquiry".equals(type)) {
				List<Inquiry> inquiryList = inquiryService.getAllNotices();
				Page<Inquiry> inquiryPage = inquiryService.getInquiryByPage(page, pageSize);
				model.addAttribute("PageNumber", inquiryPage.getNumber() + 1);
				model.addAttribute("Page", inquiryPage);
				model.addAttribute("data", inquiryList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			} else if ("qna".equals(type)) {
				List<QnA> qnaList = qnaService.getAllQnA();
				Page<QnA> qnaPage = qnaService.getQnAByPage(page, pageSize);
				model.addAttribute("PageNumber", qnaPage.getNumber() + 1);
				model.addAttribute("Page", qnaPage);
				model.addAttribute("data", qnaList);
				model.addAttribute("type", type); // Thymeleaf를 위한 'type' 속성 설정
			}
		}
		return "qna";

	}

	@GetMapping("writeQna")
	public String writeQna(HttpSession session, Model model) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);

			return "writeQna";

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("userProfile", userProfile);
			return "writeQna";

		}
		return "writeQna";
	}

	@PostMapping("writeQna")
	public String PostwriteQna(@RequestParam("title") String title, @RequestParam("content") String content,
			HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		QnA qnA = new QnA();

		LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			qnA.setAuthor(dto.getName());
			qnA.setTitle(title);
			qnA.setDate(formattedDateTime);
			qnA.setContent(content);
			qnA.setType("QnA");
			System.err.println("if문" + title + joinDate + content);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			qnA.setAuthor(userProfile.getName());
			qnA.setTitle(title);
			qnA.setDate(formattedDateTime);
			qnA.setContent(content);
			qnA.setType("QnA");

		} else {
			qnA.setAuthor("비회원 유저");
			qnA.setTitle(title);
			qnA.setDate(formattedDateTime);
			qnA.setContent(content);
			qnA.setType("QnA");
		}
		qnaService.save(qnA);
		return "redirect:/my/qna?type=qna";
	}

	@GetMapping("writeInquiry")
	public String writeInquiry(HttpSession session, Model model) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);

			return "writeInquiry";

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("userProfile", userProfile);
			return "writeInquiry";

		}
		return "writeInquiry";
	}

	@PostMapping("writeInquiry")
	public String PostwriteInquiry(@RequestParam("title") String title, @RequestParam("content") String content,
			HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		Inquiry inquiry = new Inquiry();

		LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			inquiry.setAuthor(dto.getName());
			inquiry.setTitle(title);
			inquiry.setDate(formattedDateTime);
			inquiry.setContent(content);
			inquiry.setType("inquiry");
			System.err.println("if문" + title + joinDate + content);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			inquiry.setAuthor(userProfile.getName());
			inquiry.setTitle(title);
			inquiry.setDate(formattedDateTime);
			inquiry.setContent(content);
			inquiry.setType("inquiry");

		} else {
			inquiry.setAuthor("비회원 유저");
			inquiry.setTitle(title);
			inquiry.setDate(formattedDateTime);
			inquiry.setContent(content);
			inquiry.setType("inquiry");
		}
		inquiryService.save(inquiry);
		return "redirect:/my/qna?type=inquiry";
	}

	@GetMapping(value = "/readBoard/{type}/{id}")
	public String readBoard( @PathVariable String type, @PathVariable Long id,Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			if (type.equals("inquiry")) {
				String author = inquiryRepository.selectAuthor(id);
				String title = inquiryRepository.selectTitle(id);
				String content = inquiryRepository.selectContent(id);
				String date = inquiryRepository.selectDate(id);
				System.err.println(title);
				ReadDTO readDTO = new ReadDTO();
				readDTO.setAuthor(author);
				readDTO.setTitle(title);
				readDTO.setContent(content);
				readDTO.setDate(date);
				
				
				model.addAttribute("dto2", readDTO);
			} else if (type.equals("QnA")) {
				String author = qnARepository.selectAuthor(id);
				String title = qnARepository.selectTitle(id);
				String content = qnARepository.selectContent(id);
				String date = qnARepository.selectDate(id);
				ReadDTO readDTO = new ReadDTO();
				readDTO.setAuthor(author);
				readDTO.setTitle(title);
				readDTO.setContent(content);
				readDTO.setDate(date);
				model.addAttribute("dto2", readDTO);
			}

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			if (type.equals("inquiry")) {
				String author = inquiryRepository.selectAuthor(id);
				String title = inquiryRepository.selectTitle(id);
				String content = inquiryRepository.selectContent(id);
				String date = inquiryRepository.selectDate(id);
				ReadDTO readDTO = new ReadDTO();
				readDTO.setAuthor(author);
				readDTO.setTitle(title);
				readDTO.setContent(content);
				readDTO.setDate(date);
				model.addAttribute("dto2", readDTO);
			} else if (type.equals("QnA")) {
				String author = qnARepository.selectAuthor(id);
				String title = qnARepository.selectTitle(id);
				String content = qnARepository.selectContent(id);
				String date = qnARepository.selectDate(id);
				ReadDTO readDTO = new ReadDTO();
				readDTO.setAuthor(author);
				readDTO.setTitle(title);
				readDTO.setContent(content);
				readDTO.setDate(date);
				model.addAttribute("dto2", readDTO);
			}
			

		}else {
			String author = qnARepository.selectAuthor(id);
			String title = qnARepository.selectTitle(id);
			String content = qnARepository.selectContent(id);
			String date = qnARepository.selectDate(id);
			ReadDTO readDTO = new ReadDTO();
			readDTO.setAuthor(author);
			readDTO.setTitle(title);
			readDTO.setContent(content);
			readDTO.setDate(date);
			model.addAttribute("dto2", readDTO);
		}
			
		return "readBoard";
	}
}
