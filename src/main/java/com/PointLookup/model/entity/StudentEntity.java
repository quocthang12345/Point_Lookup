package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ApiModelProperty(dataType = "String", value = "Mã sinh viên")
	@Column(name = "studentCode")
	private String studentCode;
	
	@JoinColumn(name = "person_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // cascadetype.all dùng để lưu các con chưa tồn tại trong database khhi lưu ba mẹ của chúng
	private PersonEntity person;
	
	@ApiModelProperty(dataType = "ClassEntity", value = "Lớp theo học")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
	private ClassEntity classAttend;
	
	@ApiModelProperty(dataType = "List<ScoreEntity>" , value = "Danh sách điểm của sinh viên")
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ScoreEntity> scores = new ArrayList<ScoreEntity>();
	
	@ApiModelProperty(hidden = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_subject", 
        joinColumns = { @JoinColumn(name = "student_id")}, 
        inverseJoinColumns = { @JoinColumn(name = "subject_id")})
	private List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
	
}
