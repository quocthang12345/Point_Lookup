package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class StudentDTO extends BaseDTO {
	
	private String studentCode;
	
	private String classCode;
	
	@JsonIgnore
	private List<ScoreDTO> scores = new ArrayList<ScoreDTO>();
	
	@JsonIgnore
	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>() ;
	
	@JsonIgnore
	private PersonDTO person;
}
