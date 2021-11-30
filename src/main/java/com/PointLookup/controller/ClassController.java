package com.PointLookup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.ClassDTO;
import com.PointLookup.model.entity.ClassEntity;
import com.PointLookup.service.classes.IClassSerivce;
import com.PointLookup.util.ConverterUtil;
import com.PointLookup.util.ResultMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "ClassController", value = "Thông tin Lơp")
public class ClassController {
	
	@Autowired
	private IClassSerivce classService;
	
	private ConverterUtil<ClassDTO, ClassEntity> classConverter = new ConverterUtil<ClassDTO, ClassEntity>(ClassDTO.class, ClassEntity.class);
	
	@ApiOperation(value = "Tìm kiếm Lớp theo Khoa", notes = "API này sẽ tìm kiếm lớp thông qua tên khoa")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findClassByMajor"}
	)
	public Map<String, Object> findClassByMajor(@ApiParam(value = "Mã Khoa", required = true) @RequestParam(required = true) String majorCode) {
		try {
			if(majorCode != null) {
				List<ClassEntity> classes = classService.findByMajor(majorCode);
				
				List<ClassDTO> listClassDto = new ArrayList<ClassDTO>();
				
				classes.forEach(item -> listClassDto.add(classConverter.toDTO(item)));
			
				if(listClassDto.size() <= 0) return ResultMap.createResultMap("Error", null, "Danh sách rỗng");
				
				return ResultMap.createResultMap("Success", listClassDto, "Danh sách Lớp");
			}
			return ResultMap.createResultMap("Error", null, "Mã khoa đang rỗng");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Đã có lỗi trong khi chạy");
		}
	}
	
	@ApiOperation(value = "Thêm Lớp", notes = "API này cho phép thêm lớp vào hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/addClass"}
	)
    public ResponseEntity<String> addClass
    (@ApiParam(value = "ClassInfo",required = true) @RequestBody ClassDTO classDTO,@ApiParam(value = "MajorName",required = true) @RequestParam String majorName, 
    		@ApiParam(value = "userNameTeacher",required = false)@RequestParam(required = false) String userNameTeacher) {
		try {
			if(classDTO == null && majorName == null) {
				return new ResponseEntity<String>("Tham số truyền vào null", HttpStatus.BAD_REQUEST); 
			}
			ClassEntity classes = classService.insertClass(classDTO, majorName, userNameTeacher);
			
			if(classes == null) return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
				
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
    }
	
	@ApiOperation(value = "Thêm học sinh vào lớp", notes = "API này cho phép thêm học sinh vào lớp trong hệ thống")
	@PostMapping(
			path = {"/api/addStudentInClass"}
	)
	public ResponseEntity<String> addStudentInClass(@ApiParam(value = "StudentCode",required = true) @RequestParam String studentCode,
			@ApiParam(value = "ClassCode",required = true) @RequestParam String classCode){
		try {
			if(studentCode == null && classCode == null) {
				return new ResponseEntity<String>("Tham số truyền vào null", HttpStatus.BAD_REQUEST); 
			}
			ClassEntity classes = classService.addStudentInClass(studentCode, classCode);
			
			if(classes == null) {
				return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
}
