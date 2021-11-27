package com.PointLookup.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MajorDTO extends BaseDTO {
	private String majorName;
	
	private String majorCode;
	
	@JsonIgnore
	private List<ClassDTO> listClass = new ArrayList<ClassDTO>();
}
