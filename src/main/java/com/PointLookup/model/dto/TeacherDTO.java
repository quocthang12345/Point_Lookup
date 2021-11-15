package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class TeacherDTO {
	
	private String teacherCode;
	
	private ClassDTO homeroomClass;
	
	@JsonIgnore
	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
	
	@JsonIgnore
	private PersonDTO person;
}
