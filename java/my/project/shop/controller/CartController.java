package my.project.shop.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import my.project.shop.dtos.MemberJoinDto;
import my.project.shop.dtos.ProductDto;
import my.project.shop.dtos.SaveCartDTO;
import my.project.shop.repository.CartRepository;
import my.project.shop.repository.MemberQuery;
import my.project.shop.service.MemberService;
import my.project.shop.service.ProductService;
import my.project.shop.entity.Cart;
import my.project.shop.entity.Product;
import my.project.shop.oauth2.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class CartController {

    private final ProductService productService;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    MemberQuery query;
    SaveCartDTO saveCartDTO;

    @GetMapping("/cart")
    public String cartView(Cart cart, @AuthenticationPrincipal User user, Model model, HttpSession session) {
        Object dtoObject = session.getAttribute("dto");
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;

            List<my.project.shop.entity.Cart> carts = cartRepository.findAllByMember(dto.getId());
            model.addAttribute("dto", dto);
            model.addAttribute("list", carts);

            if (cart.getProduct() != null) {
                System.out.println(cart.getProduct().getPrice());
            }

            return "cart";
        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;

            List<Cart> carts = cartRepository.findAllByMember(userProfile.getId());
            model.addAttribute("dto", userProfile);
            model.addAttribute("list", carts);

            if (cart.getProduct() != null) {
                System.out.println(cart.getProduct().getPrice());
            }

            return "cart";
        }

        return "cart";
    }

    @PostMapping("/cart")
    public String Cart(Cart cart, @AuthenticationPrincipal User user, HttpSession session) {
    	 Object save= session.getAttribute("itemcount");
        saveCartDTO = (SaveCartDTO) save;
        Product product = productService.getProductById(saveCartDTO.getItemCount());
       
        cart.setProduct(product);
        cart.getProduct().setItemcount(saveCartDTO.getItemCount());
        if (cart.getProduct() != null) {
            // 원본 product에서 itemcount 가져와서 할당
            
            System.out.println(cart.getProduct().getPrice());
        }
        Object dtoObject = session.getAttribute("dto");
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;
           
           
            cart.setMember(dto.getId());
            cartRepository.save(cart);
            return "redirect:/my/cart";
        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;
            cart.setMember(userProfile.getId());
            cartRepository.save(cart);
            return "redirect:/my/cart";
        }

        return "redirect:/my/cart";
    }

}
