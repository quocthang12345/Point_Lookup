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
	private double hardWorkScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm bài tập")
	@Column(name = "assignmentScore")
	private double assignmentScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm giữa kì")
	@Column(name = "midtermScore")
	private double midtermScore;
	
	@ApiModelProperty(dataType = "double" , value = "Điểm cuối kì")
	@Column(name = "FinalScore")
	private double FinalScore;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
	private StudentEntity student;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
	private SubjectEntity subject;
	
	
}
