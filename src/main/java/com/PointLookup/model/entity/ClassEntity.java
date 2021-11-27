package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "class")
@Data
@ApiModel(value = "Class Model")
public class ClassEntity extends BaseEntity {
	
	@ApiModelProperty(dataType = "String" , value = "Tên lớp")
	@Column(name = "className")
	@NotNull
	private String className;
	
	@ApiModelProperty(dataType = "String" , value = "Mã lớp")
	@Column(name = "classCode")
	@NotNull
	private String classCode;
	
	@ApiModelProperty(dataType = "TeacherEntity" , value = "Giáo viên chủ nhiệm")
	@JoinColumn(name = "teacher_id")
    @OneToOne(fetch = FetchType.LAZY)
	private TeacherEntity homeRoomTeacher;
	
	@ApiModelProperty(dataType = "List<StudentEntity>" , value = "Danh sách sinh viên")
	@OneToMany(mappedBy = "classAttend", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentEntity> student = new ArrayList<StudentEntity>();
	
	@ApiModelProperty(dataType = "MajorEntity" , value = "Khoa trực thuộc")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
	private MajorEntity major;
	
	@Override
	public String toString() {		
		String rs = "\n{\n"+
				"\tname: \"" + this.className + "\",\n" + 
				"\tphone: \"" + this.classCode + "\",\n" + 
				"\tTeacher: \"" + this.homeRoomTeacher.getPerson().getFullName() + "\",\n" + 
				"\tListStudent: \"" + this.getStudent().toString() + "\",\n" +
				"\tmajor: \""  + this.major.getMajorName() + "\",\n" +"\n"
				+"\n}\n";
								
		return rs;
	}
}
