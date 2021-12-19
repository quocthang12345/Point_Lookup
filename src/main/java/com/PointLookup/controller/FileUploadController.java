package com.PointLookup.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.service.classes.IClassSerivce;
import com.PointLookup.service.student.StudentService;
import com.PointLookup.service.subject.ISubjectService;
import com.PointLookup.util.ResultMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "FileUploadController", description = "Upload file csv/excel")
public class FileUploadController {
	
	@Autowired
	private IClassSerivce classService;
	
	@Autowired
	private ISubjectService subjectService;

	@PostMapping(
			  consumes = {
					  MediaType.MULTIPART_FORM_DATA_VALUE
			  },
			 path = "/api/uploadStudentInClass"
			  )
	  public Map<String, Object> uploadFileCSVStudentToClass(@RequestParam("file") MultipartFile file, @RequestParam("classCode") String classCode) {
	    if(file == null) {
	    	return ResultMap.createResultMap("Error", null, "file đang null");
	    }
	    String extension = file.getContentType();
	    List<String[]> allData = new ArrayList<String[]>();
	    List<List<String>> result = new ArrayList<List<String>>();
	    try {
	      if(extension.equals("text/csv")) {
	    	  Reader reader = new InputStreamReader(file.getInputStream());
	    	  
	          // create csvReader object and skip first Line
	    	  CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	    	  
	          allData = csvReader.readAll();
	          
	          for(String[] row : allData) {
	        	  List<String> newRow = new ArrayList<String>();
	        	  for(String cell: row) {
	        		  if(cell.equals("")) continue;
	        		  
	        		  newRow.add(cell);
	        	  }
	        	  result.add(newRow);
	          }
	          
	          result.forEach(item -> {
	        	 classService.addStudentInClass(item.get(0), classCode); 
	          });
	         
	          if(allData.size() > 0) return ResultMap.createResultMap("Success", null, "Đã thêm thành công");  
	      }
	      return ResultMap.createResultMap("Error", null, "Extension không được hỗ trợ");
	    } catch (Exception e) {
	      return ResultMap.createResultMap("Error", null, "Đã xảy ra lỗi trong quá trình upload");
	    }
	  }
	
	@PostMapping(
			  consumes = {
					  MediaType.MULTIPART_FORM_DATA_VALUE
			  },
			 path = "/api/uploadStudentInSubject"
			  )
	  public Map<String, Object> uploadFileCSVStudentToSubject(@RequestParam("file") MultipartFile file, @RequestParam("subjectCode") String subjectCode) {
	    if(file == null) {
	    	return ResultMap.createResultMap("Error", null, "file đang null");
	    }
	    String extension = file.getContentType();
	    List<String[]> allData = new ArrayList<String[]>();
	    List<List<String>> result = new ArrayList<List<String>>();
	    try {
	      if(extension.equals("text/csv")) {
	    	  Reader reader = new InputStreamReader(file.getInputStream());
	    	  
	          // create csvReader object and skip first Line
	    	  CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	    	  
	          allData = csvReader.readAll();
	          
	          for(String[] row : allData) {
	        	  List<String> newRow = new ArrayList<String>();
	        	  for(String cell: row) {
	        		  if(cell.equals("")) continue;
	        		  
	        		  newRow.add(cell);
	        	  }
	        	  result.add(newRow);
	          }
	          
	          result.forEach(item -> {
	        	 StudentDTO student = new StudentDTO();
	        	 student.setStudentCode(item.get(0));
	        	 student.setClassCode(item.get(1));
	        	 subjectService.addListStudentInSubject(student, subjectCode);
	          });
	         
	          if(allData.size() > 0) return ResultMap.createResultMap("Success", null, "Đã thêm thành công");  
	      }
	      return ResultMap.createResultMap("Error", null, "Extension không được hỗ trợ");
	    } catch (Exception e) {
	      return ResultMap.createResultMap("Error", null, "Đã xảy ra lỗi trong quá trình upload");
	    }
	  }
}
