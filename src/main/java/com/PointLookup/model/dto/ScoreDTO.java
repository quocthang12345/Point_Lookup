package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data 
public class ScoreDTO extends BaseDTO {
	
	private Double hardWorkScore;

	private Double assignmentScore;

	private Double midtermScore;
	
	private Double FinalScore;
	
	@JsonIgnore
	private List<StudentDTO> students = new ArrayList<StudentDTO>();
	
	@JsonIgnore
	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
}
