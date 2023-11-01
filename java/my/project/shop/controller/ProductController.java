package my.project.shop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.dtos.ProductDto;
import my.project.shop.dtos.ReviewDTO;
import my.project.shop.dtos.ReviewPageRequestDTO;
import my.project.shop.dtos.ReviewPageResultDTO;
import my.project.shop.dtos.SaveCartDTO;
import my.project.shop.entity.Product;
import my.project.shop.entity.Review;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.MemberQuery;
import my.project.shop.service.CategoryService;

import my.project.shop.service.ReviewService;
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class ProductController {
    ProductDto productDto = new ProductDto();
    private final CategoryService categoryService;
    private final MemberQuery query;
    private final ReviewService reviewService;
     Review review;
     SaveCartDTO saveCartDTO = new SaveCartDTO();

    @GetMapping("/addProduct")
    public String showAddProductForm(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	}
	return "addProduct"; // 상품 등록 폼 페이지로 이동
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, Model model, @RequestParam("upFiles") MultipartFile imageFile) {
        // 업로드된 이미지 파일의 이름을 추출
        String imageName = imageFile.getOriginalFilename();

        // 이미지 파일 이름을 Product 엔터티에 설정
        product.setImg(imageName);
        System.err.println(imageName);
        // 상품 정보를 저장
        categoryService.saveJavaEntity(product);

        // 다시 상품 목록 페이지로 리다이렉트
        return "redirect:/my/main";
    }

    @GetMapping(value = "/productDetail/{productId}")
    public String productDetail(@RequestParam(name = "page", defaultValue = "1") int page, @PathVariable Long productId,
	    Model model, HttpSession session,
	    @ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO) {
    	Object dtoObject = session.getAttribute("dto");
    	saveCartDTO.setItemCount(productId);
    	session.setAttribute("itemcount", saveCartDTO);
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    int pageSize = 3; // 페이지당 리뷰 수
	    
	    Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	    System.err.println(reviewPage.getNumber());
	    model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	    
	    model.addAttribute("reviewPage", reviewPage);
	    List<Review> reviews = reviewService.fetchReviewsForProduct(productId);
	    model.addAttribute("reviews", reviews);
	    
	    productDto.setDate(query.selectDate(productId));
	    productDto.setTitle(query.selectTitle(productId));
	    productDto.setProvider(query.selectProvider(productId));
	    productDto.setPrice(query.selectPrice(productId));
	    productDto.setDescription(query.selectDescription(productId));
	    productDto.setImg(query.selectImg(productId));
	    productDto.setCategory(query.selectCategory(productId));

	    model.addAttribute("dto", dto);

	    model.addAttribute("product", productDto);

	    return "/productdetail";
	}

	else if (dtoObject instanceof UserProfile) {
	    int pageSize = 3; // 페이지당 리뷰 수
	    Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	    System.err.println(reviewPage.getNumber());
	    model.addAttribute("reviewPage", reviewPage);
	    model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	    UserProfile userProfile = (UserProfile) dtoObject;
	    List<Review> reviews = reviewService.fetchReviewsForProduct(productId);
	    model.addAttribute("reviews", reviews);
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);

	    model.addAttribute("reviewResult", reviewResult);
	    productDto.setDate(query.selectDate(productId));
	    productDto.setTitle(query.selectTitle(productId));
	    productDto.setProvider(query.selectProvider(productId));
	    productDto.setPrice(query.selectPrice(productId));
	    productDto.setDescription(query.selectDescription(productId));
	    productDto.setImg(query.selectImg(productId));
	    productDto.setCategory(query.selectCategory(productId));
	    
	    model.addAttribute("product", productDto);
	    model.addAttribute("dto", userProfile);

	    return "/productdetail";

	} else {
	    int pageSize = 3; // 페이지당 리뷰 수
	    Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	    
	    model.addAttribute("reviewPage", reviewPage);
	    model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	    List<Review> reviews = reviewService.fetchReviewsForProduct(productId);
	    model.addAttribute("reviews", reviews);
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);

	    model.addAttribute("reviewResult", reviewResult);
	    productDto.setDate(query.selectDate(productId));
	    productDto.setTitle(query.selectTitle(productId));
	    productDto.setProvider(query.selectProvider(productId));
	    productDto.setPrice(query.selectPrice(productId));
	    productDto.setDescription(query.selectDescription(productId));
	    productDto.setImg(query.selectImg(productId));
	    productDto.setCategory(query.selectCategory(productId));
	    
	    model.addAttribute("product", productDto);
	    return "/productdetail";
	}
    }
}
