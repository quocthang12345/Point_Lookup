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
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "person")
public class PersonEntity extends BaseEntity {
	@Column(name = "username")
	private String userName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "isVerified")
	private boolean isVerified;
	
	@Column(name = "provider")
	private String provider;
	
	@Column(name = "providerId")
	private String providerId;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "password")
	private String passWord;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "status")
	private int status;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_roles", 
        joinColumns = { @JoinColumn(name = "person_id")}, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private List<RoleEntity> roles = new ArrayList<>();
}
