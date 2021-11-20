package com.PointLookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.model.entity.SubjectEntity;
import com.PointLookup.service.subject.ISubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "SubjectController", value = "Thông tin môn học")
public class SubjectController {
	
	@Autowired
	private ISubjectService subjectService;
	
	@ApiOperation(value = "Lấy tất cả danh sách môn học", notes = "API này sẽ tìm kiếm tất cả môn học có trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/findAllSubject"
	)
	public ResponseEntity<String> findAllSubject() {
		try {
			List<SubjectEntity> subjecties = subjectService.findAllSubject();
			
			if(subjecties == null) return new ResponseEntity<String>("Tìm kiếm thất bại hoặc môn học không tồn tại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>(subjecties.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tìm kiếm thất bại", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Lấy tất cả môn đang học của sinh viên", notes = "API này cho phép lấy tất cả môn học học sinh tham gia vào trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/listSubjectOfStudent"}
	)
    public ResponseEntity<String> getListSubjectOfStudent(@ApiParam(value = "StudentCode",required = true)@RequestParam String studentCode) {
		try {
			if(studentCode == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}			
			List<SubjectEntity> subjects = subjectService.findAllSubjectByStudent(studentCode);
			
			if(subjects.size() == 0) return new ResponseEntity<String>("Danh sách rỗng", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>(subjects.toArray().toString(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Thêm môn học", notes = "API này cho phép thêm môn học vào hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/addSubject"}
	)
    public ResponseEntity<String> addSubject(@ApiParam(value = "SubjectInfo",required = true) @RequestBody SubjectDTO subjectDto) {
		try {
			if(subjectDto == null) {
				return new ResponseEntity<String>("Giá trị truyền vào null", HttpStatus.BAD_REQUEST);
			}
			SubjectEntity subject = subjectService.addSubject(subjectDto);
			
			if(subject == null) return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}	
    }
	
	@ApiOperation(value = "Thêm học sinh tham gia vào môn học", notes = "API này cho phép thêm học sinh tham gia vào môn học trong hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/addStudentInSubject"}
	)
    public ResponseEntity<String> addStudentInSubject(@ApiParam(value = "ListStudentInfo",required = true) @RequestBody List<StudentDTO> listStudentDto, 
    		@ApiParam(value = "SubjectCode",required = true)@RequestParam String subjectCode) {
		try {
			if(listStudentDto.size() == 0 && subjectCode == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}
			
			SubjectEntity subject = subjectService.addListStudentInSubject(listStudentDto, subjectCode);
			
			if(subject == null) return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
	
}
