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

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.model.entity.SubjectEntity;
import com.PointLookup.service.subject.ISubjectService;
import com.PointLookup.util.ConverterUtil;
import com.PointLookup.util.ResultMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "SubjectController", value = "Thông tin môn học")
public class SubjectController {
	
	@Autowired
	private ISubjectService subjectService;
	
	private ConverterUtil<SubjectDTO, SubjectEntity> subjectConverter = new ConverterUtil<SubjectDTO, SubjectEntity>(SubjectDTO.class, SubjectEntity.class);
	
	private ConverterUtil<StudentDTO, StudentEntity> studentConverter = new ConverterUtil<StudentDTO, StudentEntity>(StudentDTO.class, StudentEntity.class);
	
	@ApiOperation(value = "Lấy tất cả danh sách môn học", notes = "API này sẽ tìm kiếm tất cả môn học có trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findAllSubject"}
	)
	public Map<String, Object> findAllSubject() {
		try {
			List<SubjectEntity> subjecties = subjectService.findAllSubject();
			
			if(subjecties == null) return ResultMap.createResultMap("Error", null, "Tìm kiếm thất bại hoặc môn học không tồn tại");
			
			List<SubjectDTO> listSubjectDto = new ArrayList<SubjectDTO>();
			
			subjecties.forEach(item -> listSubjectDto.add(subjectConverter.toDTO(item)));
			
			return ResultMap.createResultMap("Success", listSubjectDto, "Danh sách tất cả môn học");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Tìm kiếm thất bại");
		}
	}
	@ApiOperation(value = "Lấy tất cả môn đang học của sinh viên", notes = "API này cho phép lấy tất cả môn học học sinh tham gia vào trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/listStudentOfSubject"}
	)
    public Map<String, Object> getListStudentOfSubject(@ApiParam(value = "SubjectCode",required = true)@RequestParam String subjectCode) {
		try {
			if(subjectCode == null) {
				return ResultMap.createResultMap("Error", null, "Giá trị truyền vào đang rỗng");
			}			
			List<StudentEntity> students = subjectService.findAllStudentBySubject(subjectCode);
			
			if(students.size() == 0) return ResultMap.createResultMap("Error", null, "Danh sách rỗng");
			
			List<StudentDTO> listStudentDto = new ArrayList<StudentDTO>();
			
			students.forEach(item -> listStudentDto.add(studentConverter.toDTO(item)));
			
			return ResultMap.createResultMap("Success", listStudentDto, "Danh sách tất cả môn học của sinh viên");
		}catch (Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Đã có lỗi trong khi chạy");
		}
	}
	
	@ApiOperation(value = "Lấy tất cả môn đang học của sinh viên", notes = "API này cho phép lấy tất cả môn học học sinh tham gia vào trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/listSubjectOfStudent"}
	)
    public Map<String, Object> getListSubjectOfStudent(@ApiParam(value = "StudentCode",required = true)@RequestParam String studentCode) {
		try {
			if(studentCode == null) {
				return ResultMap.createResultMap("Error", null, "Giá trị truyền vào đang rỗng");
			}			
			List<SubjectEntity> subjects = subjectService.findAllSubjectByStudent(studentCode);
			
			if(subjects.size() == 0) return ResultMap.createResultMap("Error", null, "Danh sách rỗng");
			
			List<SubjectDTO> listSubjectDto = new ArrayList<SubjectDTO>();
			
			subjects.forEach(item -> listSubjectDto.add(subjectConverter.toDTO(item)));
			
			return ResultMap.createResultMap("Success", listSubjectDto, "Danh sách tất cả môn học của sinh viên");
		}catch (Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Đã có lỗi trong khi chạy");
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
