package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "subject")
@ApiModel(value = "Subject Model")
public class SubjectEntity extends BaseEntity{
	@ApiModelProperty(dataType = "String" , value = "Tên Môn")
	@Column(name = "subjectName")
	@NotNull
	private String subjectName;
	
	@ApiModelProperty(dataType = "String" , value = "Mã Môn")
	@Column(name = "subjectCode")
	@NotNull
	private String subjectCode;
	
	@ApiModelProperty(dataType = "TeacherEntity" , value = "Giáo viên giảng dạy")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
	private TeacherEntity teacher;
	
	@ApiModelProperty(dataType = "List<ScoreEntity>" , value = "Danh sách điểm của môn của từng sinh viên")
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ScoreEntity> scores = new ArrayList<ScoreEntity>();
	
	@ApiModelProperty(hidden = true)
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            },
			mappedBy = "subjects")
	private List<StudentEntity> student = new ArrayList<StudentEntity>();
	
	
}
