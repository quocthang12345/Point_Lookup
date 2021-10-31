package com.PointLookup.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BaseDTO {

	@JsonIgnore
	private Long id;
	
	@JsonIgnore
	private Date createDate;
	
	@JsonIgnore
	private Date updateDate;
	
	@JsonIgnore
	private String createBy;
	
	@JsonIgnore
	private String updateBy;
	
	
	
}
