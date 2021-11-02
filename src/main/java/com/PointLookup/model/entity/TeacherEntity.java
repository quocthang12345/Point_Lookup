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
@Table(name = "teacher")
@ApiModel(value = "Teacher Model")
public class TeacherEntity extends BaseEntity{
	
	
//	@ApiModelProperty(dataType = "List<String>", value = "Danh sách lớp dạy")
//	@Column(name = "listClassTeach")
//	private List<String> listClassTeach;
//	
//	@ApiModelProperty(dataType = "List<String>", value = "Danh sách khoa công tác")
//	@Column(name = "listClassTeach")
//	private List<String> listMajor; 
	
	@ApiModelProperty(dataType = "List<String>", value = "Lớp chủ nhiệm/sinh hoạt")
	@Column(name = "listClassTeach")
	private String homeroomClass;
	
	@ApiModelProperty(dataType = "String", value = "Mã giáo viên")
	@Column(name = "teacherCode")
	private String teacherCode;
	
	@JoinColumn(name = "teacher_id")
    @OneToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
	
}
