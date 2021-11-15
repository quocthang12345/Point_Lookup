package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ScoreDTO {
	
	private double hardWorkScore;

	private double assignmentScore;

	private double midtermScore;
	
	private double FinalScore;
	
	@JsonIgnore
	private List<StudentDTO> students = new ArrayList<StudentDTO>();
	
	@JsonIgnore
	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
}
