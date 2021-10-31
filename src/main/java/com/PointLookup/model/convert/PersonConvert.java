package com.PointLookup.model.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.model.entity.TeacherEntity;
import com.PointLookup.repository.IStudentRepository;
import com.PointLookup.repository.ITeacherRepository;


@Component
public class PersonConvert {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PersonDTO toDTO(PersonEntity personEntity) {
		PersonDTO person = modelMapper.map(personEntity,PersonDTO.class);
		return person;
	}
	
	public PersonEntity toEntity(PersonDTO personDto) {
		PersonEntity person = modelMapper.map(personDto, PersonEntity.class);

		 if(personDto.getStudent() != null) { 
			 StudentEntity student = new StudentEntity();
			 student.setClassAttend(personDto.getStudent().getClassAttend());
			 student.setMarjorAttend(personDto.getStudent().getMarjorAttend());
			 student.setPerson(person);
			 person.setStudent(student); 
		 }else if(personDto.getTeacher() != null) { 
			 TeacherEntity teacher = new TeacherEntity();
			 teacher.setHomeroomClass(personDto.getTeacher().getHomeroomClass());
			 teacher.setPerson(person);
			 person.setTeacher(teacher); 
		}
	    return person;
	}
}
