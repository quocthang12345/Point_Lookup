package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SubjectDTO {
	private String subjectName;
	
	private String subjectCode;
	
	private String teacherCode;
	
	@JsonIgnore
	private List<ScoreDTO> scores = new ArrayList<ScoreDTO>();
	
	@JsonIgnore
	private List<StudentDTO> students = new ArrayList<StudentDTO>();
}
