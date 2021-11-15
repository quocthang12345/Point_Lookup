package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ClassDTO {
	
	private String className;
	
	private String classCode;
	
	@JsonIgnore
	private TeacherDTO teacher;
	
	@JsonIgnore
	private List<StudentDTO> students = new ArrayList<StudentDTO>();
	
	@JsonIgnore
	private MajorDTO major;
}
