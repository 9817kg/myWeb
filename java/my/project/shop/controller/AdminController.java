package my.project.shop.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.entity.Inquiry;
import my.project.shop.entity.Member;
import my.project.shop.entity.Notice;
import my.project.shop.entity.QnA;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.service.MemberService;
import my.project.shop.service.NoticeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private NoticeService noticeService;

	@GetMapping("/user_list")
	public String user_list(Model model) {
		// 전체 회원 목록을 가져온다
		List<Member> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "user_list";
	}

	@DeleteMapping("/deleteMember/{memberId}")
	@ResponseBody
	public String deleteMember(@PathVariable Long memberId) {
		try {
			// 회원을 삭제하는 비즈니스 로직 수행
			memberService.deleteMemberById(memberId);
			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}
	}

	@GetMapping("/admin")
    public String collection(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "admin";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "admin"; // online-store.html 페이지로 이동
	}
	return "admin";
    }

	@PostMapping("/admin")
	public String adminPost(@RequestParam("title") String title, @RequestParam("content") String content) {
		LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setAuthor("관리자");
		notice.setContent(content);
		notice.setDate(formattedDateTime);
		noticeService.save(notice);

		return "redirect:/admin/admin";
	}
	 @GetMapping("/notice_manage")
		public String notice_manager(@RequestParam(name = "page", defaultValue = "1") int page, Model model, HttpSession session) {
			int pageSize = 6; // 페이지당 리뷰 수

			Object dtoObject = session.getAttribute("dto");
			if (dtoObject instanceof MemberJoinDto) {
				MemberJoinDto dto = (MemberJoinDto) dtoObject;
				model.addAttribute("dto", dto);

				List<Notice> noticeList = noticeService.getAllNotices();
				Page<Notice> noticePage = noticeService.getNoticeByPage(page, pageSize);
				model.addAttribute("PageNumber", noticePage.getNumber() + 1);
				model.addAttribute("Page", noticePage);

				model.addAttribute("data", noticeList);

			} else if (dtoObject instanceof UserProfile) {
				UserProfile userProfile = (UserProfile) dtoObject;
				model.addAttribute("dto", userProfile);

				List<Notice> noticeList = noticeService.getAllNotices();
				Page<Notice> noticePage = noticeService.getNoticeByPage(page, pageSize);
				model.addAttribute("PageNumber", noticePage.getNumber() + 1);
				model.addAttribute("Page", noticePage);

				model.addAttribute("data", noticeList);

			} else {

				List<Notice> noticeList = noticeService.getAllNotices();
				Page<Notice> noticePage = noticeService.getNoticeByPage(page, pageSize);
				model.addAttribute("PageNumber", noticePage.getNumber() + 1);
				model.addAttribute("Page", noticePage);

				model.addAttribute("data", noticeList);

			}
			return "notice_manage";

		}
	
	@DeleteMapping("/deleteNotice/{noticeId}")
	@ResponseBody
	public String deleteNotice(@PathVariable Long noticeId) {
		try {
			// 회원을 삭제하는 비즈니스 로직 수행
			noticeService.deleteNoticeById(noticeId);
			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}
	}
	
	@GetMapping("/modifyNotice/{noticeId}")
	public String modifyNotice(@PathVariable Long noticeId, Model model) {
		Optional<Notice> notice = noticeService.getFindId(noticeId);
	    model.addAttribute("notice", notice);
	    return "modifyNotice"; // 수정 페이지의 이름
	}
	
	@PostMapping("/updateNotice/{noticeId}")
	public String updateNotice(@PathVariable Long noticeId, @RequestParam("title") String title, @RequestParam("content") String content) {
	    Optional<Notice> optionalNotice = noticeService.getFindId(noticeId);
	    LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);
	    if (optionalNotice.isPresent()) {
	    	
	        Notice notice = optionalNotice.get();
	        notice.setTitle(title);
	        notice.setContent(content);
	        notice.setDate(formattedDateTime);
	        noticeService.save(notice);
	    }
	    
	    return "redirect:/admin/notice_manage"; // 수정 후, 공지사항 관리 페이지로 이동
	}
	
	

	
}
