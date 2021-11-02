package com.PointLookup.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentRepository studentRepository;
	
	@Override
	public StudentEntity findByStudentCode(String studentCode) {
		try {
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			if(student == null) {
				return null;
			}
			return student;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StudentEntity updateStudent(StudentDTO studentDto) {
		try {
			StudentEntity student = studentRepository.findByStudentCode(studentDto.getStudentCode());
			if(student == null) {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public boolean deleteByStudentCode(String studentCode) {
		try {
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			if(student == null) {
				return false;
			}
			boolean isDeleted = studentRepository.deleteByStudentCode(studentCode);
			if(!isDeleted) return false;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
