package com.PointLookup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.service.auth.tokenProvider.JwtTokenProvider;
import com.PointLookup.service.person.IPersonService;
import com.PointLookup.util.ConverterUtil;
import com.PointLookup.util.ResultMap;

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
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	private ConverterUtil<PersonDTO, PersonEntity> personConverter = new ConverterUtil<PersonDTO, PersonEntity>(PersonDTO.class, PersonEntity.class);

	@ApiResponses(value = {
		@ApiResponse(responseCode = "300", description = "This is Error Page 300"),
		@ApiResponse(responseCode = "403", description = "Sorry, you don't have permission to access this api."),
		@ApiResponse(responseCode = "500", description = "This is Error Page 500"),
		@ApiResponse(responseCode = "401", description = "Sorry, you can authorize to access this api.")
			 })
	
	@GetMapping("/api/listPerson")
    public Map<String, Object> findPersonByRole(@ApiParam(value = "Mã vai trò", required = true) @RequestParam(value = "roleCode") String roleCode) {
		try {
			if(roleCode == null) return ResultMap.createResultMap("Error", null, "Mã vai trò đang null"); 
			
			List<PersonEntity> listPerson = personService.findListPersonByRole(roleCode);
			
			if(listPerson.size() <= 0 || listPerson == null) return ResultMap.createResultMap("Error", null, "Danh sách rỗng");
			
			List<PersonDTO> listPersonDto = new ArrayList<PersonDTO>();
			
			listPerson.forEach(person -> {
				listPersonDto.add(personConverter.toDTO(person));
			});
				
			return ResultMap.createResultMap("Success", listPersonDto, "Danh sách người dùng theo vai trò");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
 		
    }
	
	@GetMapping("/api/findPerson")
    public Map<String, Object> findPersonByRequest(HttpServletRequest request) {
		try {
			if(request == null) return ResultMap.createResultMap("Error", null, "request null"); 
			
			PersonEntity person = personService.findPersonByToken(request);
			
			if(person == null) return ResultMap.createResultMap("Error", null, "Người dùng không tồn tại");
			
			PersonDTO personDto = personConverter.toDTO(person);
			
			return ResultMap.createResultMap("Success", personDto, "Thông tin người dùng");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
 		
    }
	
	@GetMapping("/api/findByUserName")
    public Map<String, Object> findPersonByUserName(@ApiParam(value = "Tên tài khoản", required = true) @RequestParam(value = "username") String username) {
		try {
			if(username == null) return ResultMap.createResultMap("Error", null, "Tên tài khoản đang trống"); 
			
			PersonEntity person = personService.findPersonByUsername(username);
			
			if(person == null) return ResultMap.createResultMap("Error", null, "Người dùng không tồn tại");
			
			PersonDTO personDto = personConverter.toDTO(person);
			
			return ResultMap.createResultMap("Success", personDto, "Thông tin người dùng");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
 		
    }
	
	@ApiOperation(value = "Tìm tất cả người dùng", notes = "API này sẽ trả về tất cả người dùng trong hệ thống")
	@GetMapping(path = {"/api/findAllPerson"})
	public Map<String, Object> getAllPerson() {
		List<PersonEntity> persons = personService.findAll();
		
		List<PersonDTO> listPersonDto = new ArrayList<PersonDTO>();
		
		if(persons == null) return ResultMap.createResultMap("Error", null, "Danh sách rỗng");
		
		persons.forEach(item -> listPersonDto.add(personConverter.toDTO(item)));
		
		return ResultMap.createResultMap("Success", listPersonDto, "Danh sách người dùng");
	}
	
	@ApiOperation(value = "Tìm tất cả người dùng qua trạng thái hoạt động", notes = "API này sẽ tìm tất cả người dùng qua status")
	@GetMapping(path = {"/api/getPersonStatus"})
	public Map<String, Object> findAllPersonExist(@ApiParam(value = "Nhập trạng thái (0/1)", required = true) @RequestParam(value = "status") int status) {
		try {
			if(status != 1 || status != 0) {
				return ResultMap.createResultMap("Error", null, "Xin vui lòng nhập đúng giá trị trạng thái");
			}
			List<PersonEntity> listPerson = personService.findByStatus(status);
			
			List<PersonDTO> listPersonDto = new ArrayList<PersonDTO>();
			if(listPerson == null) {
				return ResultMap.createResultMap("Error", null, "Không tìm thấy người dùng");
			}
			
			listPerson.forEach(item -> listPersonDto.add(personConverter.toDTO(item)));
			
			return ResultMap.createResultMap("Success", listPerson, "Danh sách người dùng hoạt động");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMap.createResultMap("Error", null, "Không tìm thấy người dùng");
		}
	}
	
	@ApiOperation(value = "Cập nhật người dùng", notes = "API này sẽ cập nhật người dùng trong hệ thống")
	@PutMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/updatePerson"}
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
	@DeleteMapping(path = {"/api/deleteUser"})
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
