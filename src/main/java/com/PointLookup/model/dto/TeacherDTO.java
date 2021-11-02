package com.PointLookup.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class TeacherDTO {
	@JsonIgnore
	private String teacher_id;
	
	private String homeroomClass;
	
	private String teacherCode;
}
