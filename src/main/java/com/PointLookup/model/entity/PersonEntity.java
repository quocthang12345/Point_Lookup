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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "person")
@ApiModel(value = "Person Model")
public class PersonEntity extends BaseEntity {
	
	@ApiModelProperty(dataType = "String" , value = "Tên đăng nhập")
	@Column(name = "username")
	@NotNull
	private String userName;
	
	@ApiModelProperty(dataType = "String" , value = "Email")
	@NotNull
	@Column(name = "email")
	private String email;
	
	
	@ApiModelProperty(hidden = true)
	@Column(name = "verifyCode")
	private String verifyCode;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "isVerified")
	private boolean isVerified;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "provider")
	private String provider;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "providerId")
	private String providerId;
	
	@ApiModelProperty(dataType = "String", value = "Ảnh đại diện của người dùng")
	@Column(name = "avatar")
	private String avatar;
	
	@ApiModelProperty(dataType = "String", value = "Mật khẩu của người dùng")
	@NotNull
	@JsonIgnore
	@Column(name = "password")
	private String passWord;
	
	@ApiModelProperty(dataType = "String", value = "Họ và Tên")
	@Column(name = "fullname")
	private String fullName;
	
	@ApiModelProperty(dataType = "String", value = "Số điện thoại")
	@Column(name = "phone")
	private String phone;
	
	@ApiModelProperty(dataType = "String", value = "Địa chỉ")
	@Column(name = "address")
	private String address;
	
	@ApiModelProperty(dataType = "String", value = "Thành phố")
	@Column(name = "city")
	private String city;
	
	@ApiModelProperty(hidden = true)
	@Column(name = "status")
	private int status;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private StudentEntity student; 
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private TeacherEntity teacher; 
	
	@ApiModelProperty(hidden = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_roles", 
        joinColumns = { @JoinColumn(name = "person_id")}, 
        inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private List<RoleEntity> roles = new ArrayList<>();
	
	
	
	
	
	@Override
	public String toString() {		
		String rs = "\n{\n"+
				"\tname: \"" + this.userName + "\",\n" + 
				"\tphone: \"" + this.address + "\",\n" + 
				"\taddress: \"" + this.avatar + "\",\n" + 
				"\tdescription: \"" + this.address + "\",\n" +
				"\temail: \""  + this.email + "\",\n" +"\n";
								
		return rs;
	}
	
}
