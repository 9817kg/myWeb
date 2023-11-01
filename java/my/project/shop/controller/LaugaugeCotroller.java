package my.project.shop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.entity.Product;
import my.project.shop.entity.Review;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.MemberQuery;
import my.project.shop.service.CategoryService;
import my.project.shop.service.MemberService;
import my.project.shop.service.ProductService;
import my.project.shop.service.ReviewService;



@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class LaugaugeCotroller {
    
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    @GetMapping("/java")
    public String java(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("java"); // "java" 카테고리에 해당하는 메서드로 변경

	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	model.addAttribute("reviewPage", reviewPage);
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "java";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "java";
	}
	return "java";
    }
    @GetMapping("/python")
    public String python(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("python"); // "java" 카테고리에 해당하는 메서드로 변경
	model.addAttribute("list", javaEntities);
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPageNumber", reviewPage.getNumber()+1);
	model.addAttribute("reviewPage", reviewPage);
	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "python";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "python";
	}
	return "python";
    }
    @GetMapping("/springboot")
    public String springboot(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("springboot"); // "java" 카테고리에 해당하는 메서드로 변경
	model.addAttribute("list", javaEntities);
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPage", reviewPage);
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "springboot";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "springboot";
	}
	return "springboot";
    }
    @GetMapping("/hs") // html/css 카테고리
    public String  hs(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("hs"); // "java" 카테고리에 해당하는 메서드로 변경
	model.addAttribute("list", javaEntities);
	
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	model.addAttribute("reviewPage", reviewPage);
	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "hs";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "hs";
	}
	return "hs";
    }
    @GetMapping("/javascript")
    public String javascript(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("javascript"); // "java" 카테고리에 해당하는 메서드로 변경
	model.addAttribute("list", javaEntities);
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	model.addAttribute("reviewPage", reviewPage);
	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "javascript";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "javascript";
	}
	return "javascript";
    }
    @GetMapping("/react")
    public String react(HttpSession session, Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
	// Java 카테고리에 해당하는 Product 목록 가져오기
	List<Product> javaEntities = categoryService.getProductsByCategory("react"); // "java" 카테고리에 해당하는 메서드로 변경
	model.addAttribute("list", javaEntities);
	int pageSize = 6; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	model.addAttribute("reviewPage", reviewPage);
	// 모델에 목록 추가
	model.addAttribute("list", javaEntities);
	
	// 나머지 코드는 유지
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "react";
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "react";
	}
	return "react";
    }
    
}
