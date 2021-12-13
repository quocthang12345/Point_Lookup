package com.PointLookup.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "score")
@ApiModel(value = "Score Model")
public class ScoreEntity extends BaseEntity{
	
	@ApiModelProperty(dataType = "double" , value = "Điểm chuyên cần")
	@Column(name = "hardWorkScore")
	private Double hardWorkScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm bài tập")
	@Column(name = "assignmentScore")
	private Double assignmentScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm giữa kì")
	@Column(name = "midtermScore")
	private Double midtermScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm cuối kì")
	@Column(name = "FinalScore")
	private Double FinalScore;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
	private StudentEntity student;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
	private SubjectEntity subject;
	
	
	@Override
	public String toString() {		
		String rs = "\n{\n"+
				"\thardWorkScore: \"" + this.hardWorkScore + "\",\n" + 
				"\tassignmentScore: \"" + this.assignmentScore + "\",\n" + 
				"\tmidtermScore: \"" + this.midtermScore + "\",\n" + 
				"\tFinalScore: \"" + this.FinalScore + "\",\n" +
				"\tstudent: \""  + this.student + "\",\n" +"\n" +
				"\tsubject: \""  + this.subject + "\",\n" +
				"}\n";
								
		return rs;
	}
	
}
