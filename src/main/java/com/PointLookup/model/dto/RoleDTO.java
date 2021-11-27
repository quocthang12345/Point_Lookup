package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RoleDTO {
	private String roleName;
	
	private String roleCode;
	
    private List<PersonDTO> persons = new ArrayList<>();
}
