package com.PointLookup.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class RoleEntity extends BaseEntity {
	@Column(name = "name")
	private String roleName;
	
	@Column(name = "code")
	private String roleCode;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            },
			mappedBy = "roles")
    private List<PersonEntity> persons = new ArrayList<>();
}
