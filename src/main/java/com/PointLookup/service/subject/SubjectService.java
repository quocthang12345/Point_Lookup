package com.PointLookup.service.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.model.entity.SubjectEntity;
import com.PointLookup.model.entity.TeacherEntity;
import com.PointLookup.repository.ISubjectRepository;
import com.PointLookup.repository.ITeacherRepository;
import com.PointLookup.util.ConverterUtil;

@Service
public class SubjectService implements ISubjectService {

	@Autowired
	private ITeacherRepository teacherRepository;
	
	@Autowired
	private ISubjectRepository subjectRepository;
	
	private ConverterUtil<SubjectDTO, SubjectEntity> subjectConvert = new ConverterUtil<SubjectDTO, SubjectEntity>(SubjectDTO.class, SubjectEntity.class);
	
	@Override
	public List<SubjectEntity> findAllSubject() {
		return subjectRepository.findAll();
	}

	@Override
	public SubjectEntity addSubject(SubjectDTO subjectDto) {
		try {
			TeacherEntity teacher = teacherRepository.findByTeacherCode(subjectDto.getTeacherCode());
			
			if(teacher == null) {
				return null;
			}
			
			SubjectEntity subject = subjectConvert.toEntity(subjectDto);
			
			subject.setTeacher(teacher);
			
			teacher.getSubjects().add(subject);
			
			SubjectEntity subjectInserted = subjectRepository.save(subject);
			
			return subjectInserted;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SubjectEntity addListStudentInSubject(List<StudentDTO> studentDto, String subjectCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
