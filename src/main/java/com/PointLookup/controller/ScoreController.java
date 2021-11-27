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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.ScoreDTO;
import com.PointLookup.model.entity.ScoreEntity;
import com.PointLookup.service.score.IScoreService;
import com.PointLookup.util.ConverterUtil;
import com.PointLookup.util.ResultMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "ScoreController", value = "Thông tin điểm")
public class ScoreController {
	@Autowired
	private IScoreService scoreService;
	
	private ConverterUtil<ScoreDTO, ScoreEntity> scoreConverter = new ConverterUtil<ScoreDTO, ScoreEntity>(ScoreDTO.class, ScoreEntity.class);
	
	@ApiOperation(value = "Tìm kiếm điểm môn học theo mã sinh viên", notes = "API này sẽ tìm kiểm điểm môn học theo mã sinh viên")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findScoreByStudentCode"}
	)
	public Map<String, Object> findScoreSubjectByStudentCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam(required = true) String studentCode,
			@ApiParam(value = "Mã môn học", required = true) @RequestParam(required = true) String subjectCode) {
		try {
			if(studentCode == null && subjectCode == null) {
				return ResultMap.createResultMap("Error", null, "Tham số truyền vào đang null");
			}
			ScoreEntity score = scoreService.findBySubjectOfStudent(subjectCode, studentCode);
			
			ScoreDTO scoreDto = scoreConverter.toDTO(score);
			
			if(scoreDto == null) return ResultMap.createResultMap("Error", null, "Tìm kiếm thất bại");
			
			return ResultMap.createResultMap("Success", scoreDto, "Điểm môn học của sinh viên");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Đã có lỗi trong lúc chạy");
		}
	}
	
	@ApiOperation(value = "Tìm kiếm tất cả điểm môn học theo mã sinh viên", notes = "API này sẽ tìm kiểm tất cả điểm môn học theo mã sinh viên")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findAllScoreByStudentCode"}
	)
	public Map<String, Object> findAllScoreSubjectByStudentCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam(required = true) String studentCode) {
		try {
			if(studentCode == null) {
				return ResultMap.createResultMap("Error", null, "Tham số truyền vào đang null");
			}
			List<ScoreEntity> listScore = scoreService.findByAllSubjectOfStudent(studentCode);
			
			List<ScoreDTO> listScoreDto = new ArrayList<ScoreDTO>();
			listScore.forEach(item -> listScoreDto.add(scoreConverter.toDTO(item)));
			
			if(listScoreDto.size() <= 0) return ResultMap.createResultMap("Error", null, "Tìm kiếm thất bại");
			
			return ResultMap.createResultMap("Success", listScoreDto, "Tất cả điểm môn học của sinh viên");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Đã có lỗi trong lúc chạy");
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
