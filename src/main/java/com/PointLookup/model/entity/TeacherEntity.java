package com.PointLookup.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	
	@ApiModelProperty(dataType = "String", value = "Mã giáo viên")
	@Column(name = "teacherCode")
	private String teacherCode;
	
	@ApiModelProperty(dataType = "List<String>", value = "Lớp chủ nhiệm/sinh hoạt")
	@OneToOne(mappedBy = "homeRoomTeacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	private ClassEntity homeRoomClass;
	
	@JoinColumn(name = "person_id")
    @OneToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
	
	@ApiModelProperty(dataType = "List<SubjectEntity>", value = "Danh sách môn dạy")
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubjectEntity> subjects;
	
	
}
