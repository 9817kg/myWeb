package my.project.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageRequestDTO;
import my.project.shop.dtos.ReviewPageResultDTO;
import my.project.shop.dtos.TeachaerDTO;
import my.project.shop.entity.Member;
import my.project.shop.entity.Product;
import my.project.shop.entity.Review;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.MemberQuery;
import my.project.shop.repository.MemberRepository;
import my.project.shop.repository.TeacherQuery;
import my.project.shop.service.MemberService;

@Controller
@RequestMapping("/info")
public class ModifyMemberInfoController {
    private MemberQuery query;
    @Autowired
    private TeacherQuery teacherQuery;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository repository;

    @Autowired // passwordEncoder 주입
    public ModifyMemberInfoController(MemberQuery query, PasswordEncoder passwordEncoder) {
	this.query = query;
	this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "mypage";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "mypage";
	}
	return "mypage";
    }
    @GetMapping("/myinfoTeacher")
    public String myinfoTeacher(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "myinfoTeacher";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "myinfoTeacher";
	}
	return "myinfoTeacher";
    }
    
    @GetMapping("/changTeacher")
    public String changTeacher(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "changTeacher";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);

	    return "changTeacher";
	}

	return "changTeacher";
    }

    @PostMapping("/changTeacher")
    public String changTeacherPost(HttpSession session, @ModelAttribute Member member, Model model,
	    @RequestParam("upLoad") MultipartFile upLoad, @RequestParam("name") String name,
	    @RequestParam("email") String email, @RequestParam("intro") String intro,@RequestParam("career") String career) {
	// 업로드된 이미지 파일의 이름을 추출
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    String imageName = upLoad.getOriginalFilename();
	    System.err.println(imageName);

	    
	    // 이미지 파일 이름을 Product 엔터티에 설정

	    query.updateProfileTeacher(imageName, email, name, intro,career, dto.getEmail());
	    System.err.println(dto.getEmail());
	    System.err.println(imageName);
	    dto.setCareer(career);
	    dto.setIntroduce(intro);
	    dto.setEmail(email);
	    dto.setName(name);
	    dto.setProfile(imageName);
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "redirect:/info/myinfo";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);

	    String imageName = upLoad.getOriginalFilename();
	    System.err.println(imageName);

	   
	    // 이미지 파일 이름을 Product 엔터티에 설정
	    userProfile.setCareer(career);
	    userProfile.setIntroduce(intro);
	    userProfile.setEmail(email);
	    userProfile.setName(name);
	    userProfile.setProfile(imageName);
	    query.updateProfileTeacher(imageName, email, name, intro,career, userProfile.getEmail());
	    System.err.println(imageName);
	    System.err.println(userProfile.getEmail());

	    return "redirect:/info/myinfoTeacher";
	}

	return "redirect:/info/myinfoTeacher";
    }
    

    @GetMapping(value = "/teacherInfo/{teacher}")
    public String productDetail( @PathVariable String teacher, Model model,
	    HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	TeachaerDTO teacherDto = new TeachaerDTO();

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    teacherDto.setCareer(teacherQuery.selectCareer(teacher));
	    teacherDto.setEmail(teacherQuery.selectEmail(teacher));
	    teacherDto.setName(teacherQuery.selectName(teacher));
	    teacherDto.setIntroduce(teacherQuery.selectIntro(teacher));
	    model.addAttribute("teacher", teacherDto);

	    return "/teacherInfo";
	}

	else if (dtoObject instanceof UserProfile) {

	    UserProfile userProfile = (UserProfile) dtoObject;

	    teacherDto.setCareer(teacherQuery.selectCareer(teacher));
	    teacherDto.setEmail(teacherQuery.selectEmail(teacher));
	    teacherDto.setName(teacherQuery.selectName(teacher));
	    teacherDto.setIntroduce(teacherQuery.selectIntro(teacher));
	    model.addAttribute("teacher", teacherDto);
	    model.addAttribute("dto", userProfile);

	    return "/teacherInfo";

	} else {
	    teacherDto.setCareer(teacherQuery.selectCareer(teacher));
	    teacherDto.setEmail(teacherQuery.selectEmail(teacher));
	    teacherDto.setName(teacherQuery.selectName(teacher));
	    teacherDto.setIntroduce(teacherQuery.selectIntro(teacher));
	    model.addAttribute("teacher", teacherDto);
	    return "/teacherInfo";
	}
	
    }
    
    
    

    @GetMapping("/mypage2")
    public String mypage2(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "mypage2";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "mypage2";
	}

	return "mypage2";
    }

    @GetMapping("/manageacc")
    public String manageacc(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "manageacc";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "manageacc";
	}

	return "manageacc";
    }

    @GetMapping("/manageaccSocial")
    public String manageaccSocial(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "manageaccSocial";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "manageaccSocial";
	}

	return "manageaccSocial";
    }

    @GetMapping("/myinfo")
    public String myinfo(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "myinfo";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);

	    return "myinfo";
	}

	return "myinfo";
    }

    @GetMapping("/changInfo")
    public String changeInfo(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "changInfo";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);

	    return "changInfo";
	}

	return "changInfo";
    }

    @PostMapping("/changInfo")
    public String changProfile(HttpSession session, @ModelAttribute Member member, Model model,
	    @RequestParam("upLoad") MultipartFile upLoad, @RequestParam("name") String name,
	    @RequestParam("email") String email, @RequestParam("birth") String birth) {
	// 업로드된 이미지 파일의 이름을 추출
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    String imageName = upLoad.getOriginalFilename();
	    System.err.println(imageName);

	    member.setEmail(email);
	    member.setBirth(birth);
	    member.setName(name);

	    // 이미지 파일 이름을 Product 엔터티에 설정

	    query.updateProfile(imageName, email, name, birth, dto.getEmail());
	    System.err.println(dto.getEmail());
	    System.err.println(imageName);
	    dto.setBirth(birth);
	    dto.setEmail(email);
	    dto.setName(name);
	    dto.setProfile(imageName);
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "redirect:/info/myinfo";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);

	    String imageName = upLoad.getOriginalFilename();
	    System.err.println(imageName);

	    member.setEmail(email);
	    member.setBirth(birth);
	    member.setName(name);
	    // 이미지 파일 이름을 Product 엔터티에 설정
	    userProfile.setBirth(birth);
	    userProfile.setEmail(email);
	    userProfile.setName(name);
	    userProfile.setProfile(imageName);
	    query.updateProfile(imageName, email, name, birth, userProfile.getEmail());
	    System.err.println(imageName);
	    System.err.println(userProfile.getEmail());

	    return "redirect:/info/myinfo";
	}

	return "redirect:/info/myinfo";
    }

    @GetMapping("/managepw")
    public String managepw(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "managepw";
	}

	return "managepw";
    }

    @PostMapping("/managepw")
    public String changePw(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
	    @RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");

	    // 현재 비밀번호와 저장된 비밀번호를 비교하여 일치하는지 확인
	    if (passwordEncoder.matches(password, dto.getPassword())) {
		// 새 비밀번호와 확인 비밀번호가 일치하는지 확인
		if (newPassword.equals(confirmPassword)) {
		    // 새 비밀번호를 암호화하여 저장
		    String hashedNewPassword = passwordEncoder.encode(newPassword);
		    query.updatePassword(hashedNewPassword, dto.getPassword());
		    dto.setPassword(hashedNewPassword);
		    model.addAttribute("dto", dto);
		    session.setAttribute("dto", dto);

		    // 비밀번호 변경 성공 시 로그아웃 후 메인 페이지로 리다이렉트
		    return "redirect:/view/login";
		} else {
		    model.addAttribute("dto", dto);
		    return "redirect:/info/managepw";
		}
	    }
	}
	return "managepw";
    }

    @GetMapping("/managemobile")
    public String managemobile(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {

	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "managemobile";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "managemobile";
	}
	return "managemobile";
    }

    @PostMapping("/managemobile")
    public String postManagemobile(@RequestParam("") String newPhoneNumber, RedirectAttributes redirectAttributes,
	    Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    query.updateMobileByMobile(newPhoneNumber, dto.getEmail());
	    dto.setPhone(newPhoneNumber);
	    redirectAttributes.addAttribute("message", "변경완료");
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "redirect:/info/mypage";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    query.updateMobileByMobile(newPhoneNumber, userProfile.getEmail());
	    userProfile.setMobile(newPhoneNumber);
	    model.addAttribute("dto", userProfile);
	    redirectAttributes.addAttribute("message", "변경완료");
	    session.setAttribute("dto", userProfile);
	    return "redirect:/info/mypage2";
	} else {

	    return "redirect:/info/managemobile";
	}
    }

    @GetMapping("/checkAddress")
    public String checkAddress(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {

	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "checkAddress";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "checkAddress";
	}
	return "checkAddress";
    }

    @PostMapping("/checkAddress")
    public String PostcheckAddress(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {

	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    session.setAttribute("dto", dto);
	    return "redirect:/info/manageaddress";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    session.setAttribute("dto", userProfile);
	    return "redirect:/info/manageaddress";
	}
	return "redirect:/info/manageaddress";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {

	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "withdraw";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "withdraw";
	}
	return "withdraw";
    }

    @PostMapping("/withdraw")
    public String postWithdraw(@RequestParam("email") String email, @RequestParam("password") String password,
	    Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
	if (passwordEncoder.matches(password, dto.getPassword())) {
	    query.remove(dto.getPassword(), dto.getEmail());
	    model.addAttribute("dto", dto);
	    redirectAttributes.addAttribute("message", "탈퇴완료");
	    return "redirect:/my/main";
	} else if (!passwordEncoder.matches(password, dto.getPassword())) {
	    redirectAttributes.addAttribute("message", "탈퇴실패");
	    return "redirect:/info/withdraw";
	}
	return "withdraw";

    }

    @GetMapping("/withdraw2")
    public String withdraw2(Model model, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);

	    return "withdraw2";
	}
	return "withdraw";
    }

    @PostMapping("/withdraw2")
    public String postWithdraw2(@RequestParam("email") String email, Model model, HttpSession session,
	    RedirectAttributes redirectAttributes) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    if (email.equals(userProfile.getEmail())) {
		query.remove2(email);
		redirectAttributes.addAttribute("message", "탈퇴완료");
		return "redirect:/my/main";
	    }
	} else {
	    return "redirect:/info/withdraw2";
	}

	return "withdraw2";

    }
}
