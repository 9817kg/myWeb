package my.project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import my.project.shop.config.AdminAuthorize;
import my.project.shop.config.UserAuthorize;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.entity.Member;
import my.project.shop.oauth2.OAuth2Service;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.MemberQuery;
import my.project.shop.service.MemberService;

@Controller
@RequestMapping("/view")
public class ViewController {

	@Autowired
	private MemberQuery query;
	@Autowired
	private OAuth2Service oAuth2Service; // OAuth2Service 주입 추가
	MemberJoinDto dto = new MemberJoinDto();

	Member member = new Member();
	MemberService memberService;
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/join")
	public String joinPage() {
		return "join";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/mainLog")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model, HttpSession session) {
		if (user != null) {
			String email = user.getUsername();
			dto.setId(query.selectId(email));
			dto.setEmail(email);
			dto.setPassword(query.selectPw(email));
			dto.setName(query.selectName(email));
			dto.setProvider(query.selectProvider(email));
			dto.setMemberType(email);
			dto.setProfile(query.selectProfile(email));
			// 핸드폰 번호
			dto.setPhone(query.selectMobilebyEmail(email));
			// 생일
			dto.setBirth(query.selectBirth(email));
			// 권한
			dto.setRole(query.selectRole(email));
			dto.setCareer(query.selectCareer(email));
			dto.setIntroduce(query.selectIntro(email));
			dto.setMemberType(query.selectType(email));

			System.out.println("mainLog dto:" + dto);
			session.setAttribute("dto", dto);
			model.addAttribute("dto", dto);
			return "mainLog";
		}

		return "mainLog";

	}

	@GetMapping("/mainLog2")
	public String dashboardPage2(@AuthenticationPrincipal User user, Model model, HttpSession session) {
		if (user == null) {

			UserProfile userProfile = (UserProfile) session.getAttribute("dto");
			
			userProfile.setId(query.selectId(userProfile.getEmail()));
			userProfile.setMemberType(query.selectMemberType(userProfile.getMemberType()));
			userProfile.setProvider(query.selectProvider(userProfile.getEmail()));
			// 핸드폰 번호
			userProfile.setMobile(query.selectPh(userProfile.getEmail()));
			// 생일
			userProfile.setBirth(query.selectBirth(userProfile.getEmail()));
			// 권한
			userProfile.setRole(query.selectRole(userProfile.getEmail()));
			
			System.err.println("mainLog dto:" + userProfile.getEmail());
			session.setAttribute("dto", userProfile);
			model.addAttribute("dto", userProfile);
		}
		return "mainLog2";

	}

	@GetMapping("/setting/admin")
	@AdminAuthorize
	public String adminSettingPage() {
		return "admin_setting";
	}

	@GetMapping("/setting/user")
	@UserAuthorize
	public String userSettingPage() {
		return "user_setting";
	}
}