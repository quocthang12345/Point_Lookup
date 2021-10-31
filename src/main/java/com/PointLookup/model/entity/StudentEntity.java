package com.PointLookup.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
@ApiModel(value = "Student Model")
public class StudentEntity extends BaseEntity{
	
	
	@ApiModelProperty(dataType = "String", value = "Lớp theo học")
	@Column(name = "classAttend")
	private String classAttend;
	
	@ApiModelProperty(dataType = "String", value = "Ngành theo học")
	@Column(name = "marjorAttend")
	private String marjorAttend;
	
	@JoinColumn(name = "student_id")
    @OneToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
	
}
