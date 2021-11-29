package com.PointLookup.model.dto;

import lombok.Data;

@Data 
public class ScoreDTO extends BaseDTO {
	
	private Double hardWorkScore;

	private Double assignmentScore;

	private Double midtermScore;
	
	private Double FinalScore;
	
	private StudentDTO students;
	
	private SubjectDTO subjects;
}
