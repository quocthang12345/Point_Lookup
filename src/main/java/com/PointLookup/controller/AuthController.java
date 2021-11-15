package com.PointLookup.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.PointLookup.model.dto.MyUserDetail;
import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.resource.ERole;
import com.PointLookup.model.resource.LoginRequest;
import com.PointLookup.service.auth.IAuthService;
import com.PointLookup.service.auth.tokenProvider.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = "AuthController", value = "Đăng ký, đăng nhập và phân quyền", description = "Đăng ký, đăng nhập và phân quyền")
public class AuthController {

	@Autowired
	private IAuthService authService;
	
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@ApiResponses(value = {
		@ApiResponse(responseCode = "300", description = "This is Error Page 300"),
		@ApiResponse(responseCode = "403", description = "Sorry, you don't have permission to access this api."),
		@ApiResponse(responseCode = "500", description = "This is Error Page 500"),
		@ApiResponse(responseCode = "401", description = "Sorry, you can authorize to access this api.")
			 })
	
	@ApiOperation(value = "Đăng nhập", notes = "API này cho người dùng đăng nhập vào hệ thống và trả về người dùng token nếu sucess")
	@PostMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/login"}
	)
    public ResponseEntity<String> loginUser(@ApiParam(value = "LoginRequest",required = true)@RequestBody(required = true) LoginRequest loginRequest) {
        try {
            // Xác thực từ username và password.
        	if(loginRequest.getUsername() == "" || loginRequest.getPassword() == ""){
        		return new ResponseEntity<String>("Tên đăng nhập hoặc mật khẩu đang bị trống", HttpStatus.BAD_REQUEST);
        	}
            Authentication authentication = authenticationManager.authenticate(
            		new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            		);
            
            if(authentication == null) {
            	return new ResponseEntity<String>("Đăng nhâp không thành công", HttpStatus.BAD_REQUEST);
            }
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((MyUserDetail) authentication.getPrincipal());
            return new ResponseEntity<String>(jwt, HttpStatus.OK);
        }catch(Exception e){
        	e.printStackTrace();
        	return new ResponseEntity<String>("Đăng nhập không thành công", HttpStatus.BAD_REQUEST);
        }  

    }
	
	
	@ApiOperation(value = "Đăng ký", notes = "API này cho đăng ký tài khoản vào hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/register"}
	)
    public ResponseEntity<String> registerUser(@ApiParam(value = "PersonInfo",required = true) @RequestBody PersonDTO personDto) {
		try {
			
			if(personDto.getUserName() == null) return new ResponseEntity<String>("username không có", HttpStatus.BAD_REQUEST);
			
			PersonEntity checkPersonExists = authService.findOneByUserNameAndStatus(personDto.getUserName(),1);
			boolean isRegister = false;
			if(checkPersonExists == null) {
				if(personDto.getStudentCode() != null && personDto.getTeacherCode() == null) {
					isRegister = authService.RegisterUser(personDto, ERole.STUDENT.name());
				}else if(personDto.getTeacherCode() != null && personDto.getStudentCode()== null) {
					isRegister = authService.RegisterUser(personDto, ERole.TEACHER.name());
				}
				if(isRegister) {				
					return new ResponseEntity<String>("Đăng ký thành công", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Đăng ký thất bại", HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<String>("Người dùng đã tồn tại!", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
		
    }
	
	
	
	@ApiOperation(value = "Xác thực Email", notes = "API này sẽ xác thực email khi người dùng đăng ký thành công")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/verifyCode"}
	)
    public ResponseEntity<String> checkVerifyCode(@RequestParam(value = "code") String code) {
		  try {
			  PersonEntity person = authService.findOneByVerifyCode(code);
			  if(person == null || person.isVerified()) {
				  new ResponseEntity<String>("Không tìm thấy người dùng hoặc người dùng đã xác thực Email", HttpStatus.NOT_FOUND);
			  }
			  person.setVerifyCode(code);
			  person.setVerified(true);
			  PersonEntity updatePersonSuccess = authService.UpdateOrInsertUser(person);
			  if(updatePersonSuccess != null) new ResponseEntity<String>("Cập nhật thất bại", HttpStatus.EXPECTATION_FAILED);
			  return new ResponseEntity<String>("Xác thực Email thành công", HttpStatus.OK);
		  }catch(Exception e) {
			  e.printStackTrace();
			  return new ResponseEntity<String>("Xác thực Email thất bại", HttpStatus.BAD_REQUEST);
		  }
		  
    }
	
	@ApiOperation(value = "Gửi lại Email xác thực", notes = "API này sẽ gửi lại mail xác thực")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/sendVerifyEmail"}
	)
    public ResponseEntity<String> sendEmailVerifyAgain(@RequestParam(value = "token") String token) {
		try {
			if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
	        	String username = tokenProvider.getUsernameFromJWT(token);
	        	PersonEntity person = authService.findOneByUserNameAndStatus(username, 1);
	        	authService.sendMailToVerify(person);
	        	return new ResponseEntity<String>("Gửi lại Email thành công", HttpStatus.OK);
	        }
			return new ResponseEntity<String>("Token không tồn tại", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Gửi lại Email xác thực thất bại", HttpStatus.BAD_REQUEST); 
		}		
    }
	
	@ApiOperation(value = "Đăng nhập bằng Google", notes = "API này đăng nhập social với Google")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/signInGoogle"}
	)
    public ResponseEntity<String> loginWithGoogle(@RequestParam(value = "urlRedirect") String urlRedirect) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String baseURL = "http://localhost:8080";
			ResponseEntity<String> response = restTemplate.getForEntity(baseURL + "/oauth2/authorize/google?redirect_uri=" + urlRedirect, String.class);
			return new ResponseEntity<String>(response.getHeaders().getLocation().toString(), HttpStatus.OK); 
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST); 
		}		
    }
	
	@ApiOperation(value = "Đăng nhập bằng Github", notes = "API này đăng nhập social với Github")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/signInGithub"}
	)
    public ResponseEntity<String> loginWithGithub(@RequestParam(value = "urlRedirect") String urlRedirect) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String baseURL = "http://localhost:8080";
			ResponseEntity<String> response = restTemplate.getForEntity(baseURL + "/oauth2/authorize/github?redirect_uri=" + urlRedirect, String.class);
			return new ResponseEntity<String>(response.getHeaders().getLocation().toString(), HttpStatus.OK); 
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST); 
		}		
    }
	
	@ApiOperation(value = "Đăng nhập bằng Facebook", notes = "API này đăng nhập social với Facebook")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/signInFacebook"}
	)
    public ResponseEntity<String> loginWithFacebook(@RequestParam(value = "urlRedirect") String urlRedirect) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String baseURL = "http://localhost:8080";
			ResponseEntity<String> response = restTemplate.getForEntity(baseURL + "/oauth2/authorize/facebook?redirect_uri=" + urlRedirect, String.class);
			return new ResponseEntity<String>(response.getHeaders().getLocation().toString(), HttpStatus.OK); 
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đăng nhập thất bại", HttpStatus.BAD_REQUEST); 
		}		
    }
	
}
