package com.PointLookup.service.classes;

import com.PointLookup.model.dto.ClassDTO;
import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.entity.ClassEntity;

public interface IClassSerivce {
	ClassEntity findByMajor(String majorCode);
	
	ClassEntity insertClass(ClassDTO classes, String majorName, String userNameTeacher);
	
	ClassEntity addStudentInClass(String studentCode, String classCode);
}
