package com.PointLookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.ScoreDTO;
import com.PointLookup.model.entity.MajorEntity;
import com.PointLookup.model.entity.ScoreEntity;
import com.PointLookup.service.score.IScoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "ScoreController", value = "Thông tin điểm")
public class ScoreController {
	@Autowired
	private IScoreService scoreService;
	
	@ApiOperation(value = "Tìm kiếm điểm môn học theo mã sinh viên", notes = "API này sẽ tìm kiểm điểm môn học theo mã sinh viên")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/findScoreByStudentCode"
	)
	public ResponseEntity<String> findScoreSubjectByStudentCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam(required = true) String studentCode,
			@ApiParam(value = "Mã môn học", required = true) @RequestParam(required = true) String subjectCode) {
		try {
			if(studentCode == null && subjectCode == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}
			ScoreEntity score = scoreService.findBySubjectOfStudent(subjectCode, studentCode);
			
			if(score == null) return new ResponseEntity<String>("Tìm kiếm thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>(score.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Tìm kiếm tất cả điểm môn học theo mã sinh viên", notes = "API này sẽ tìm kiểm tất cả điểm môn học theo mã sinh viên")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/findAllScoreByStudentCode"
	)
	public ResponseEntity<String> findAllScoreSubjectByStudentCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam(required = true) String studentCode) {
		try {
			if(studentCode == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}
			List<ScoreEntity> listScore = scoreService.findByAllSubjectOfStudent(studentCode);
			
			if(listScore == null) return new ResponseEntity<String>("Tìm kiếm thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>(listScore.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Thêm điểm môn học cho sinh viên", notes = "API này sẽ thêm điểm môn học cho sinh viên trong hệ thống")
	@PostMapping(
		consumes = {
			MediaType.APPLICATION_JSON_VALUE
		},
		path = {"/api/addScore"}
	)
	public ResponseEntity<String> addScoreToSubjectOfStudent(@ApiParam(value = "scoreDto",required = true) @RequestBody ScoreDTO scoreDto, 
    		@ApiParam(value = "Mã môn học",required = true) @RequestParam String subjectCode, 
    		@ApiParam(value = "Mã sinh viên",required = true) @RequestParam String studentCode){
		try {
			if(studentCode == null && subjectCode == null && scoreDto == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}
			
			ScoreEntity score = scoreService.addScoreToSubjectOfStudent(subjectCode, studentCode, scoreDto);
			
			if(score == null) return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Thêm điểm môn học cho sinh viên", notes = "API này sẽ thêm điểm môn học cho sinh viên trong hệ thống")
	@PutMapping(
		consumes = {
			MediaType.APPLICATION_JSON_VALUE
		},
		path = {"/api/updateScore"}
	)
	public ResponseEntity<String> UpdateScoreToSubjectOfStudent(@ApiParam(value = "scoreDto",required = true) @RequestBody ScoreDTO scoreDto, 
    		@ApiParam(value = "Mã môn học",required = true) @RequestParam String subjectCode, 
    		@ApiParam(value = "Mã sinh viên",required = true) @RequestParam String studentCode){
		try {
			if(studentCode == null && subjectCode == null && scoreDto == null) {
				return new ResponseEntity<String>("Giá trị truyền vào đang rỗng", HttpStatus.BAD_REQUEST);
			}
			
			ScoreEntity score = scoreService.updateScoreToSubjectOfStudent(subjectCode, studentCode, scoreDto);
			
			if(score == null) return new ResponseEntity<String>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Cập nhật thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
	}
}
