package com.PointLookup.service.student;

import java.util.List;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.entity.StudentEntity;

public interface IStudentService {
	StudentEntity findByStudentCode(String studentCode);
	
	StudentEntity updateStudent(StudentDTO student);
	
	boolean deleteByStudentCode(String studentCode);

	List<StudentEntity> findListByClassCode(String classCode);
}
