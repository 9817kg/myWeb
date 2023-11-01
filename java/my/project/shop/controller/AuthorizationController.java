package my.project.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.project.shop.dtos.MemberDTO;
import my.project.shop.service.RegisterMemberService;


@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    
    private final RegisterMemberService registerMemberService;

    public AuthorizationController(RegisterMemberService registerMemberService) {
        this.registerMemberService = registerMemberService;
    }

 // 회원가입 시 목록 추가할 곳
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberDTO dto) {
	 try {
	            // 여기서 MemberDTO에 있는 phone과 mobile 값을 추출해서 사용
	     		
	            registerMemberService.join(
	                dto.getEmail(), 
	                dto.getPassword(), 
	                dto.getName(), 
	                dto.getMemberType(),
	                dto.getPhone(),
	                 // 여기서 조합한 mobile 값을 전달
	                dto.getBirth(),
	                
	                dto.getRole(),
	                dto.getProvider(),
	                dto.getIntroduce(),
	                dto.getCareer(),
	                dto.getProfile()
	               
	               
	            );
	            
	            return ResponseEntity.ok("가입 성공");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
    }
}


