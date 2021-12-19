package com.PointLookup.service.classes;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.ClassDTO;
import com.PointLookup.model.dto.StudentDTO;
import com.PointLookup.model.entity.ClassEntity;
import com.PointLookup.model.entity.MajorEntity;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.repository.IAuthRepository;
import com.PointLookup.repository.IClassRepository;
import com.PointLookup.repository.IMajorRepository;
import com.PointLookup.repository.IStudentRepository;
import com.PointLookup.util.ConverterUtil;

@Service
public class ClassService implements IClassSerivce {

	@Autowired
	private IMajorRepository majorRepository;
	
	@Autowired
	private IClassRepository classRepository;
	
	@Autowired
	private IAuthRepository authRepository;
	
	@Autowired
	private IStudentRepository studentRepo;
	
	
	private ConverterUtil<ClassDTO, ClassEntity> classConverter = new ConverterUtil<ClassDTO, ClassEntity>(ClassDTO.class, ClassEntity.class);
	
	@Override
	public List<ClassEntity> findByMajor(String majorCode) {
		try {
			MajorEntity major = majorRepository.findByMajorCode(majorCode);
			
			if(major == null) return null;
			
			List<ClassEntity> classes = classRepository.findByMajor_Id(major.getId());
			
			if(classes.size() <= 0) return null;
					
			return classes;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public ClassEntity insertClass(ClassDTO classes, String majorName, String userNameTeacher) {
		try {
			if(classes == null && majorName == null) return null;
			
			ClassEntity classEntity = classConverter.toEntity(classes);
			
			PersonEntity teacher = authRepository.findOneByUserNameAndStatus(userNameTeacher, 1);
			
			Optional<RoleEntity> role = teacher.getRoles().stream().filter(s -> s.getRoleCode().equals("TEACHER")).findAny();
			
			if(role.get() == null) return null;
			
			ClassEntity classFindByTeacher = classRepository.findByHomeRoomTeacher_Id(teacher.getTeacher().getId());
			
			if(teacher != null && classFindByTeacher == null) {
				teacher.getTeacher().setHomeRoomClass(classEntity);
				
				classEntity.setHomeRoomTeacher(teacher.getTeacher());
			}else {
				return null;
			}
			
			MajorEntity major = majorRepository.findByMajorName(majorName);
			
			if(major != null) {
				major.getListClass().add(classEntity);
				
				classEntity.setMajor(major);
			}else {
				return null;
			}
			
			ClassEntity result = classRepository.save(classEntity);
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ClassEntity addStudentInClass(String studentCode,String classCode) {
		try {
			StudentEntity student = studentRepo.findByStudentCode(studentCode);
			if(student == null) {
				return null;
			}
			ClassEntity classes = classRepository.findByClassCode(classCode);
			if(classes == null) {
				return null;
			}
			
			if(classes.getStudent().contains(student)) {
				return null;
			}
			
			classes.getStudent().add(student);
			
			student.setClassAttend(classes);
			
			ClassEntity classInserted = classRepository.save(classes);
			
			if(classInserted == null) return null;
			
			return classInserted;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ClassEntity updateClassName(String className, String classCode) {
		try {
			if(className == null && classCode == null) return null;
			
			ClassEntity classEntity = classRepository.findByClassCode(classCode);
			
			if(classEntity == null) return null;
			
			classEntity.setClassName(className);
			
			ClassEntity classUpdated = classRepository.save(classEntity);
			
			return classUpdated;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
