package my.project.shop.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import my.project.shop.oauth2.OAuth2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;








@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    @Autowired
    private final OAuth2Service oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/status", "/view/join", "/auth/join","/my/","/my/main").permitAll()
                        .requestMatchers("/findEmail","/resultFindEmail","/findPw").permitAll()
                        .requestMatchers("/my/notice").permitAll()
                        .requestMatchers("/**").permitAll()
                        
                        .requestMatchers("/my/product/**").permitAll()
                        .requestMatchers("/my/productdetail/**").permitAll()
                        .requestMatchers("/my/onlinestore/**").permitAll()
                        
                        .requestMatchers("/info/teacherInfo").permitAll()
                        .requestMatchers("/view/admin").hasRole("ADMIN")
                        
                       
                        .anyRequest().authenticated()
                )
               .formLogin(login -> login
                        .loginPage("/view/login")
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        //.defaultSuccessUrl("/view/mainLog", true)
                        .defaultSuccessUrl("/", false) // 기본적으로 모든 사용자가 이동할 페이지
                        .successHandler((request, response, authentication) -> {
                            // 사용자 역할을 확인하여 조건부로 리다이렉트
                            if (authentication != null && authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin/admin"); // admin 역할을 가진 사용자
                            } else {
                                response.sendRedirect("/view/mainLog"); // 그 외의 사용자
                            }
                        })
                    
                        
                )
               .oauth2Login(oauth2Configurer -> oauth2Configurer
                       .loginPage("/view/login")
                       
                       .defaultSuccessUrl("/view/mainLog2", true) // 로그인 성공시 이동할 URL
                       .userInfoEndpoint()// 사용자가 로그인에 성공하였을 경우,
                       
                       .userService(oAuth2UserService)// 해당 서비스 로직을 타도록 설정
			)
               .logout()
	         .logoutUrl("/logout")
	         .invalidateHttpSession(true);
     

        return http.build();
    }
   
    
}