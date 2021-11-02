package com.PointLookup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.service.student.IStudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = "StudentController", value = "Thông tin sinh viên")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@ApiResponses(value = {
		@ApiResponse(responseCode = "300", description = "This is Error Page 300"),
		@ApiResponse(responseCode = "403", description = "Sorry, you don't have permission to access this api."),
		@ApiResponse(responseCode = "500", description = "This is Error Page 500"),
		@ApiResponse(responseCode = "401", description = "Sorry, you can authorize to access this api.")
			 })
	
	
	@ApiOperation(value = "Tìm kiếm sinh viên bằng mã sinh viên", notes = "API này sẽ tìm kiếm sinh viên thông qua mã sinh viên do nhà trường cấp trong hệ thống")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/findStudentByCode"
	)
	public ResponseEntity<String> findStudentByCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam String studentCode) {
		try {
			if(studentCode == null) {
				return new ResponseEntity<String>("Tìm kiếm thất bại hoặc sinh viên không tồn tại", HttpStatus.BAD_REQUEST);
			}
			StudentEntity student = studentService.findByStudentCode(studentCode);
			if(student == null) return new ResponseEntity<String>("Sinh viên không tồn tại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>(student.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tìm kiếm thất bại hoặc sinh viên không tồn tại", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Xóa sinh viên bằng mã sinh viên", notes = "API này sẽ xóa sinh viên thông qua mã sinh viên do nhà trường cấp trong hệ thống")
	@DeleteMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/DeleteStudentByCode"
	)
	public ResponseEntity<String> DeleteStudentByCode(@ApiParam(value = "Mã sinh viên", required = true) @RequestParam String studentCode) {
		try {
			if(studentCode == null) {
				return new ResponseEntity<String>("Xóa thất bại hoặc sinh viên không tồn tại", HttpStatus.BAD_REQUEST);
			}
			boolean isDeleted = studentService.deleteByStudentCode(studentCode);
			
			if(isDeleted == false) return new ResponseEntity<String>("Xóa thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Xóa thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xóa thất bại hoặc sinh viên không tồn tại", HttpStatus.BAD_REQUEST);
		}
	}
	

	
}
