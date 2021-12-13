package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ClassDTO extends BaseDTO {
	
	private String className;
	
	private String classCode;
	
	@JsonIgnore
	private TeacherDTO teacher;
	
	private List<StudentDTO> students = new ArrayList<StudentDTO>();
	
	private MajorDTO major;
}
