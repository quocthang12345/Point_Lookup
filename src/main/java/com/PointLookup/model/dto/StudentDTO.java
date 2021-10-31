package com.PointLookup.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class StudentDTO {
	@JsonIgnore
	private String student_id;
	
	private String classAttend;
	
	private String marjorAttend;
}
