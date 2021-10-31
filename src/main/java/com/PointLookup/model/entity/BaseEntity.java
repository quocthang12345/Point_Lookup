package com.PointLookup.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity{
	
	@JsonIgnore
	@Id
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(dataType = "Date" , value = "Ngày tạo")
	@Column(name = "createdate")
	@CreatedDate
	private Date createDate;
	
	@ApiModelProperty(dataType = "Date" , value = "Ngày chỉnh sửa")
	@Column(name = "modifydate")
	@LastModifiedDate
	private Date modifyDate;
	
	@ApiModelProperty(dataType = "String" , value = "Người tạo")
	@Column(name = "createby")
	@CreatedBy
	private String createBy;
	
	@ApiModelProperty(dataType = "String" , value = "Người chỉnh sửa")
	@Column(name = "modifyby")
	@LastModifiedBy
	private String modifyBy;
}
