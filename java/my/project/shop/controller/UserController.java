package my.project.shop.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import my.project.shop.dtos.MemberJoinDto;

import my.project.shop.dtos.ProductDto;
import my.project.shop.dtos.ProductResultDto;
import my.project.shop.dtos.ProductSearchResultDto;
import my.project.shop.dtos.ReviewDTO;

import my.project.shop.entity.Member;
import my.project.shop.entity.Notice;
import my.project.shop.entity.Product;
import my.project.shop.entity.Review;
import my.project.shop.oauth2.UserProfile;
import my.project.shop.repository.MemberQuery;
import my.project.shop.repository.SearchQuery;
import my.project.shop.service.CategoryService;
import my.project.shop.service.MemberService;
import my.project.shop.service.NoticeService;
import my.project.shop.service.ProductService;
import my.project.shop.service.ReviewService;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final MemberQuery query;
    private final ReviewService reviewService;
    private final ProductService productService;
    private final SearchQuery searchQuery;
    private ProductSearchResultDto productSearchResultDto;
    
 

    Review review;

    @GetMapping("/main")
    public String main() {
	return "main";
       }

   

    @GetMapping("/search")
    public String search(HttpSession session, Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
	Object dtoObject = session.getAttribute("results");
	List<ProductResultDto> results = (List<ProductResultDto>) dtoObject;
	
	int pageSize = 5; // 페이지당 리뷰 수
	Page<Review> reviewPage = reviewService.getReviewsByPage(page, pageSize);
	System.err.println(reviewPage.getNumber());
	model.addAttribute("reviewPageNumber", reviewPage.getNumber() + 1);
	model.addAttribute("reviewPage", reviewPage);
	model.addAttribute("results", results);

	Object dtoObjectUser = session.getAttribute("dto");
	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObjectUser;
	    model.addAttribute("dto", dto);
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObjectUser;
	    model.addAttribute("dto", userProfile);
	}
	return "search";
    }

    @PostMapping("/search")
    public String searchPost(@RequestParam("type") String type, @RequestParam("search") String search, Model model,
	    HttpSession session) {
	List<ProductResultDto> resultDtos = new ArrayList<>();

	if (type.equals("title")) {
	    List<String> categories = searchQuery.productCategoryWithTitle(search);
	    List<String> imgs = searchQuery.productImageWithTitle(search);
	    List<String> descriptions = searchQuery.productDescriptionWithTitle(search);
	    List<String> prices = searchQuery.productPriceWithTitle(search);
	    List<String> titles = searchQuery.productTitleWithTitle(search);
	    List<String> providers = searchQuery.productProviderWithTitle(search);
	    List<String> dates = searchQuery.productDateWithTitle(search);
	    List<Long> itemcounts = searchQuery.productcountWithTitle(search);
	    for (int i = 0; i < titles.size(); i++) {
		ProductResultDto resultDto = new ProductResultDto(); // 각각의 결과를 위한 새로운 객체 생성
		resultDto.setCategory(categories.get(i));
		resultDto.setImg(imgs.get(i));
		resultDto.setDescription(descriptions.get(i));
		resultDto.setPrice(prices.get(i));
		resultDto.setTitle(titles.get(i));
		resultDto.setProvider(providers.get(i));
		resultDto.setDate(dates.get(i));
		resultDto.setItemcount(itemcounts.get(i));
		resultDtos.add(resultDto);

	    }
	} else if (type.equals("category")) {
	    List<String> categories = searchQuery.productCategoryWithcategory(search);
	    List<String> imgs = searchQuery.productImageWithcategory(search);
	    List<String> descriptions = searchQuery.productDescriptionWithcategory(search);
	    List<String> prices = searchQuery.productPriceWithcategory(search);
	    List<String> titles = searchQuery.productTitleWithcategory(search);
	    List<String> providers = searchQuery.productProviderWithcategory(search);
	    List<String> dates = searchQuery.productDateWithcategory(search);
	    List<Long> itemcounts = searchQuery.productcountWithTitle(search);
	    for (int i = 0; i < titles.size(); i++) {
		ProductResultDto resultDto = new ProductResultDto(); // 각각의 결과를 위한 새로운 객체 생성
		resultDto.setCategory(categories.get(i));
		resultDto.setImg(imgs.get(i));
		resultDto.setDescription(descriptions.get(i));
		resultDto.setPrice(prices.get(i));
		resultDto.setTitle(titles.get(i));
		resultDto.setProvider(providers.get(i));
		resultDto.setDate(dates.get(i));
		if (i < itemcounts.size()) {
		    resultDto.setItemcount(itemcounts.get(i));
		}

		resultDtos.add(resultDto);
	    }
	} else if (type.equals("provider")) {
	    List<String> categories = searchQuery.productCategoryWithprovider(search);
	    List<String> imgs = searchQuery.productImageWithprovider(search);
	    List<String> descriptions = searchQuery.productDescriptionWithprovider(search);
	    List<String> prices = searchQuery.productPriceWithprovider(search);
	    List<String> titles = searchQuery.productTitleWithprovider(search);
	    List<String> providers = searchQuery.productProviderWithprovider(search);
	    List<String> dates = searchQuery.productDateWithprovider(search);
	    List<Long> itemcounts = searchQuery.productcountWithprovider(search);
	    for (int i = 0; i < titles.size(); i++) {
		ProductResultDto resultDto = new ProductResultDto(); // 각각의 결과를 위한 새로운 객체 생성
		resultDto.setCategory(categories.get(i));
		resultDto.setImg(imgs.get(i));
		resultDto.setDescription(descriptions.get(i));
		resultDto.setPrice(prices.get(i));
		resultDto.setTitle(titles.get(i));
		resultDto.setProvider(providers.get(i));
		resultDto.setDate(dates.get(i));
		resultDto.setItemcount(itemcounts.get(i));
		resultDtos.add(resultDto);
	    }
	}

	model.addAttribute("results", resultDtos);
	session.setAttribute("results", resultDtos);
	// 검색 결과 페이지로 이동
	return "redirect:/my/search";
    }

   

    @GetMapping("/cart2")
    public String cart2Page(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "cart2";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "cart2";
	}
	return "cart2";
    }

    @GetMapping("/community")
    public String community(HttpSession session, Model model) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    model.addAttribute("dto", dto);
	    return "community";

	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    model.addAttribute("dto", userProfile);
	    return "community";
	}
	return "community";
    }

    @PostMapping("/productDetail/{productId}/addReview")
    public String addReview(@RequestParam("reviewTitle") String title, @RequestParam("reviewText") String text,
	    @PathVariable Long productId, @ModelAttribute Review review, HttpSession session) {
	Object dtoObject = session.getAttribute("dto");

	if (dtoObject != null && dtoObject instanceof MemberJoinDto) {
	    MemberJoinDto dto = (MemberJoinDto) dtoObject;
	    review.setReviewer(dto.getName());
	} else if (dtoObject instanceof UserProfile) {
	    UserProfile userProfile = (UserProfile) dtoObject;
	    review.setReviewer(userProfile.getName());
	}

	review.setText(text);
	review.setTitle(title);

	LocalDate currentDate = LocalDate.now();

	// 날짜를 문자열로 변환하여 review에 저장합니다.
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formattedDate = currentDate.format(formatter);
	review.setDate(formattedDate);

	// 리뷰가 속하는 상품 정보를 가져와서 설정합니다.
	Product product = productService.getProductById(productId);
	review.setProduct(product);

	// 리뷰 저장
	reviewService.saveReview(review);

	// 리뷰가 저장된 후, 상세 페이지로 리다이렉트
	return "redirect:/my/productDetail/" + productId;
    }

}
