package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "major")
@Data
@ApiModel(value = "Major Model")
public class MajorEntity extends BaseEntity {
	@ApiModelProperty(dataType = "String" , value = "Tên Khoa")
	@Column(name = "majorName")
	@NotNull
	private String majorName;
	
	@ApiModelProperty(dataType = "String" , value = "Mã Khoa")
	@Column(name = "majorCode")
	@NotNull
	private String majorCode;
	
	@ApiModelProperty(dataType = "List<ClassEntity>" , value = "Các Lớp thuộc Khoa")
	@OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClassEntity> listClass = new ArrayList<ClassEntity>();
}
