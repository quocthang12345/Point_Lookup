package com.PointLookup.service.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.dto.SubjectDTO;
import com.PointLookup.model.entity.ClassEntity;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.model.entity.SubjectEntity;
import com.PointLookup.model.entity.TeacherEntity;
import com.PointLookup.repository.IClassRepository;
import com.PointLookup.repository.IStudentRepository;
import com.PointLookup.repository.ISubjectRepository;
import com.PointLookup.repository.ITeacherRepository;
import com.PointLookup.util.ConverterUtil;

@Service
public class SubjectService implements ISubjectService {

	@Autowired
	private ITeacherRepository teacherRepository;
	
	@Autowired
	private ISubjectRepository subjectRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	@Autowired
	private IClassRepository classRepository;
	
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
			
			SubjectEntity subjectExist = subjectRepository.findBySubjectCode(subjectDto.getSubjectCode());
			
			if(subjectExist != null) return null;
			
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
	public SubjectEntity addListStudentInSubject(StudentDTO studentDto, String subjectCode) {
		try {
			SubjectEntity subjectFind = subjectRepository.findBySubjectCode(subjectCode);
			
			if(subjectFind == null) return null;
			
			ClassEntity classes = classRepository.findByClassCode(studentDto.getClassCode());
			
			if(classes == null) return null;
			
			Optional<StudentEntity> studentEntity = classes.getStudent().stream().filter(student -> student.getStudentCode().equals(studentDto.getStudentCode())).findAny();
			
			if(!studentEntity.isPresent()) return null;
			
			Optional<StudentEntity> studentInSubject = subjectFind.getStudent().stream().filter(student -> student.getStudentCode().equals(studentDto.getStudentCode())).findAny();
			
			if(studentInSubject.isPresent()) return null;
			
			studentEntity.get().getSubjects().add(subjectFind);
					
			subjectFind.getStudent().add(studentEntity.get());
			
			SubjectEntity subjectInserted = subjectRepository.save(subjectFind);
			
			if(subjectInserted == null) return null;
			
			return subjectInserted;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SubjectEntity> findAllSubjectByStudent(String studentCode) {
		try {
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			
			if(student == null) return null;
			
			List<SubjectEntity> subjects = subjectRepository.findByStudent_Id(student.getId());
			
			if(subjects.size() == 0) return null;
			
			return subjects;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<StudentEntity> findAllStudentBySubject(String subjectCode) {
		try {
			SubjectEntity subject = subjectRepository.findBySubjectCode(subjectCode);
			
			if(subject == null) return null;
			
			List<StudentEntity> students = subject.getStudent();
			
			if(students.size() == 0) return null;
			
			return students;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
