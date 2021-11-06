package com.PointLookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.service.person.IPersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = "PersonController", value = "Thông tin người dùng")
public class PersonController {

	@Autowired
	private IPersonService personService;

	@ApiResponses(value = {
		@ApiResponse(responseCode = "300", description = "This is Error Page 300"),
		@ApiResponse(responseCode = "403", description = "Sorry, you don't have permission to access this api."),
		@ApiResponse(responseCode = "500", description = "This is Error Page 500"),
		@ApiResponse(responseCode = "401", description = "Sorry, you can authorize to access this api.")
			 })
	
	
	@ApiOperation(value = "Tìm tất cả người dùng", notes = "API này sẽ trả về tất cả người dùng trong hệ thống")
	@GetMapping(value = "/api/findAllPerson")
	public String getAllPerson() {
		return personService.findAll().toString();
	}
	
	@ApiOperation(value = "Tìm tất cả người dùng qua trạng thái hoạt động", notes = "API này sẽ tìm tất cả người dùng qua status")
	@GetMapping(value = "/api/getPersonStatus")
	public ResponseEntity<String> findAllPersonExist(@ApiParam(value = "Nhập trạng thái (0/1)", required = true) @RequestParam(value = "status") int status) {
		try {
			if(status != 1 || status != 0) {
				return new ResponseEntity<String>("Xin vui lòng nhập đúng giá trị trạng thái", HttpStatus.BAD_REQUEST);
			}
			List<PersonEntity> listPerson = personService.findByStatus(status);
			if(listPerson == null) {
				return new ResponseEntity<String>("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>(listPerson.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cập nhật người dùng", notes = "API này sẽ cập nhật người dùng trong hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			value = "/api/updatePerson"
			)
	public ResponseEntity<String> updatePerson(@ApiParam(value = "tên tài khoản muốn cập nhật", required = true) @RequestBody PersonDTO personDto) {
		try {
			if(personDto == null) {
				return new ResponseEntity<String>("Cập nhật không thành công", HttpStatus.BAD_REQUEST);
			}
			boolean isUpdated = personService.updatePerson(personDto);
			return (isUpdated ? ( new ResponseEntity<String>("Cập nhật thành công", HttpStatus.OK)):( new ResponseEntity<String>("Cập nhật không thành công", HttpStatus.BAD_REQUEST)));
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Cập nhật không thành công", HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Xoá người dùng bằng username", notes = "API này sẽ xóa người dùng bằng username")
	@DeleteMapping(value = "/api/deleteUser")
	public ResponseEntity<String> DeleteUser(@ApiParam(value = "tên tài khoản muốn xóa", required = true) @RequestParam(value = "username") String username) {
		try {
			if(username == null) {
				return new ResponseEntity<String>("Xóa không thành công", HttpStatus.BAD_REQUEST);
			}
			boolean isDeleted = personService.deleteUserByUserName(username);
			if(!isDeleted) {
				return new ResponseEntity<String>("Xóa không thành công", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xóa thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xóa không thành công", HttpStatus.BAD_REQUEST);
		}
	}
	
}
