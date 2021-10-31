package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.PointLookup.model.resource.ERole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "role")
@ApiModel(value = "Role Model")
public class RoleEntity extends BaseEntity {
	
	@ApiModelProperty(dataType = "String",value = "Tên vai trò")
	@Column(name = "name")
	private String roleName;
	
	@ApiModelProperty(dataType = "ERole",value = "Mã vai trò")
	@Column(name = "code")
	private String roleCode;
	
	
	@ApiModelProperty(hidden = true)
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            },
			mappedBy = "roles")
    private List<PersonEntity> persons = new ArrayList<>();
}
