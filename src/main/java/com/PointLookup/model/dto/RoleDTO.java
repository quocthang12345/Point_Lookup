package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RoleDTO {
	private String roleName;
	
	private String roleCode;
	
	@JsonIgnore
    private List<PersonDTO> persons = new ArrayList<>();
}
