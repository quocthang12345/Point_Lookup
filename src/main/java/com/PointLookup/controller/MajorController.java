package com.PointLookup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PointLookup.model.dto.MajorDTO;
import com.PointLookup.model.entity.MajorEntity;
import com.PointLookup.service.major.IMajorService;
import com.PointLookup.util.ConverterUtil;
import com.PointLookup.util.ResultMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = "MajorController", value = "Thông tin Khoa")
public class MajorController {
	
	@Autowired
	private IMajorService majorService;
	
	private ConverterUtil<MajorDTO, MajorEntity> majorConverter = new ConverterUtil<MajorDTO, MajorEntity>(MajorDTO.class, MajorEntity.class);

	@ApiResponses(value = {
			@ApiResponse(responseCode = "300", description = "This is Error Page 300"),
			@ApiResponse(responseCode = "403", description = "Sorry, you don't have permission to access this api."),
			@ApiResponse(responseCode = "500", description = "This is Error Page 500"),
			@ApiResponse(responseCode = "401", description = "Sorry, you can authorize to access this api.")
				 })
	
	@ApiOperation(value = "Tìm kiếm Khoa", notes = "API này sẽ tìm kiếm khoa thông qua tên khoa. Nếu không truyền tên khoa thì sẽ tìm kiếm tất cả khoa")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findMajor"}
	)
	public Map<String, Object> findMajor(@ApiParam(value = "Tên Khoa", required = false) @RequestParam(required = false) String majorName) {
		try {
			List<MajorEntity> listMajor = majorService.findListMajorByMajorName(majorName);
			
			List<MajorDTO> listMajorDto = new ArrayList<MajorDTO>();
			
			listMajor.forEach(item -> listMajorDto.add(majorConverter.toDTO(item)));
			
			if(listMajorDto.size() <= 0) return null;
			
			return ResultMap.createResultMap("Success", listMajorDto, "Danh sách Khoa");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ApiOperation(value = "Tìm kiếm Khoa theo mã lớp", notes = "API này sẽ tìm kiếm khoa thông qua mã lớp.")
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/findMajorByClassCode"}
	)
	public Map<String, Object> findMajorByClassCode(@ApiParam(value = "Mã lớp", required = false) @RequestParam(required = true) String classCode) {
		try {
			MajorEntity major = majorService.findMajorByClassCode(classCode);
			
			MajorDTO MajorDto = majorConverter.toDTO(major);
			
			if(MajorDto == null) return null;
			
			return ResultMap.createResultMap("Success", MajorDto, "Kết quả Khoa");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ApiOperation(value = "Thêm Khoa", notes = "API này cho phép thêm khoa vào hệ thống")
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/addMajor"}
	)
    public ResponseEntity<String> addMajor(@ApiParam(value = "MajorInfo",required = true) @RequestBody MajorDTO majorDto) {
		try {
			MajorEntity checkMajorExists = majorService.findByMajorCode(majorDto.getMajorCode());
			if(checkMajorExists == null) {
				MajorEntity majorAddSuccess = majorService.addMajor(majorDto);
				if(majorAddSuccess == null) {	
					return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
				}			
				return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Khoa đã tồn tại!", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}	
    }
	
	@ApiOperation(value = "Cập nhật Khoa", notes = "API này cho phép cập nhật khoa vào hệ thống")
	@PutMapping(
			path = {"/api/updateMajor"}
	)
    public ResponseEntity<String> updateMajor(@ApiParam(value = "Chỉ được cập nhật tên khoa, truyền value cho majorCode đúng",required = true) @RequestBody MajorDTO majorDto) {
		try {
			if(majorDto != null) {
				MajorEntity majorAddSuccess = majorService.updateMajor(majorDto);
				if(majorAddSuccess == null) {	
					return new ResponseEntity<String>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
				}			
				return new ResponseEntity<String>("Cập nhật thành công", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cập nhật thành công", HttpStatus.BAD_REQUEST);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>("Đã có lỗi trong khi chạy", HttpStatus.BAD_REQUEST);
		}
    }
	
	
	@ApiOperation(value = "Xóa khoa bằng mã khoa", notes = "API này sẽ xóa khoa thông qua mã khoa trong hệ thống")
	@DeleteMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/api/DeleteMajorByCode"}
	)
	public ResponseEntity<String> DeleteMajorByCode(@ApiParam(value = "Mã khoa", required = true) @RequestParam String majorCode) {
		try {
			if(majorCode == null) {
				return new ResponseEntity<String>("Xóa thất bại hoặc khoa không tồn tại", HttpStatus.BAD_REQUEST);
			}
			boolean isDeleted = majorService.deleteByMajorCode(majorCode);
			
			if(isDeleted == false) return new ResponseEntity<String>("Xóa thất bại", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<String>("Xóa thành công", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xóa thất bại hoặc khoa không tồn tại", HttpStatus.BAD_REQUEST);
		}
	}
	
}
