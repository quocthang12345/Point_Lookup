package com.PointLookup.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.PointLookup.service.auth.tokenProvider.JwtAuthenticationFilter;
import com.PointLookup.service.auth.userDetail.CustomUserDetailService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private CustomUserDetailService userDetail;
		
//		@Autowired
//	    private CustomOAuth2UserService customOAuth2UserService;

//	    @Autowired
//	    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//	    @Autowired
//	    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

//	    @Autowired
//	    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

		
		
		 @Bean
		 public JwtAuthenticationFilter jwtAuthenticationFilter() {
		        return new JwtAuthenticationFilter(); //filter request để check token Đăng nhập
		 }
		 
		@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        // Get AuthenticationManager bean
	        return super.authenticationManagerBean();
	    }
		
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // encoding password 
	    }
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetail) // Cung cáp userservice cho spring security
	            .passwordEncoder(passwordEncoder()); // cung cấp password encoder
	       
	    }
	    
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type","X-Frame-Options"));
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTION"));
	        configuration.setExposedHeaders(List.of("Authorization"));
	        configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
		
		
		
//		 @Bean
//		 public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
//		    return new HttpCookieOAuth2AuthorizationRequestRepository(); // cấu hình cookies cho đăng nhập Oauth2
//		 }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
//	    	 http.cors()
//	    	 .and()
//	    	 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Cấu hình stateless cho session
//	         .and()
//	         .csrf().disable()
//	             .authorizeRequests()
//	             .antMatchers("/",
//	                     "/error",
//	                     "/favicon.ico",
//	                     "/**/*.png",
//	                     "/**/*.gif",
//	                     "/**/*.svg",
//	                     "/**/*.jpg",
//	                     "/**/*.html",
//	                     "/**/*.css",
//	                     "/**/*.js").permitAll() // cho phép truy cập vào tài nguyên
//	             .antMatchers("/oauth2/**").permitAll()
//	             .antMatchers("/api/login").permitAll()
//	             .antMatchers("/ws/**").permitAll()
//	             .antMatchers("/app/**").permitAll()
//	             .antMatchers("/topic/**").permitAll()
//	             .antMatchers("/api/verify**").permitAll()
//	             .antMatchers("/api/sendVerifyEmail**").permitAll()
//	             .antMatchers("/api/register").permitAll()// Cho phép tất cả mọi người truy cập vào địa chỉ này
//	             .antMatchers("/api/getCollectionFashion/**").permitAll()
//	             .antMatchers("/api/getMenu").permitAll()
//	             .antMatchers("/api/getListJourney").permitAll()
//	             .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
//	             .and()
//	             .oauth2Login()
//	             .authorizationEndpoint()
//	                 .baseUri("/oauth2/authorize") // set base URI cho yêu cầu xác thưc Oauth2
//	                 .authorizationRequestRepository(cookieAuthorizationRequestRepository()) // set Cookies lưu trữ cho yêu cầu xác thực
//	                 .and()
//	             .redirectionEndpoint()
//	                 .baseUri("/oauth2/callback/*")// set base URI cho yêu cầu cho request chuyển hướng
//	                 .and()
//	             .userInfoEndpoint()
//	                 .userService(customOAuth2UserService) // get info của user thông qua service custom
//	                 .and()
//	             .successHandler(oAuth2AuthenticationSuccessHandler) // Các thao tác khi xác thực thành công
//	             .failureHandler(oAuth2AuthenticationFailureHandler) // Các thao tác nếu xác thực thất bại
//	    	 	 .and().exceptionHandling(e -> e
//	    	                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//	    	             ).oauth2Login();
	    	 http.cors()
	    	 .and()
	    	 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Cấu hình stateless cho session
	         .and()
	         .csrf().disable()
	             .authorizeRequests()
	             .antMatchers("/",
                 "/error",
                 "/favicon.ico",
                 "/**/*.png",
                 "/**/*.gif",
                 "/**/*.svg",
                 "/**/*.jpg",
                 "/**/*.html",
                 "/**/*.css",
                 "/**/*.js").permitAll()
	             .antMatchers("/v2/api-docs",
                         "/configuration/ui",
                         "/swagger-resources/**",
                         "/configuration/security",
                         "/swagger-ui.html",
                         "/webjars/**").permitAll()
	             .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
	             .antMatchers("/api/login", "/api/register").permitAll()
	             .anyRequest().authenticated();
	    	 	
	         http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);// thêm filter trước mỗi request truy cập vào tài nguyên của server
//	    	 http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	    }
	    
	    

}
