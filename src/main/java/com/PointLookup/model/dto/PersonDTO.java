package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PersonDTO extends BaseDTO {
	private String userName;
	
	private String email;
	
	@JsonIgnore
	private boolean isVerified;
	
	@JsonIgnore
	private String verifyCode;
	
	@JsonIgnore
	private String provider;
	
	@JsonIgnore
	private String providerId;
	
	private String avatar;
	
	private String passWord;
	
	private String fullName;
	
	private String phone;
	
	private String address;

	private String city;
	
	@JsonIgnore
	private int status;
	
	@JsonIgnore
    private List<RoleDTO> roles = new ArrayList<>();
    
    private StudentDTO student;
    
    private TeacherDTO teacher;    
}
