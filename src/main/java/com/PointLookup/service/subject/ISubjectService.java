package com.PointLookup.service.subject;

import java.util.List;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.model.entity.SubjectEntity;

public interface ISubjectService {

	List<SubjectEntity> findAllSubject();

	SubjectEntity addSubject(SubjectDTO subjectDto);
	
	SubjectEntity addListStudentInSubject(List<StudentDTO> studentDto, String subjectCode);
	
	List<SubjectEntity> findAllSubjectByStudent(String studentCode);

}
