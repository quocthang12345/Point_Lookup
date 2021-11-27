package com.PointLookup.service.auth;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.model.entity.TeacherEntity;
import com.PointLookup.repository.IAuthRepository;
import com.PointLookup.repository.IRoleRepository;
import com.PointLookup.repository.IStudentRepository;
import com.PointLookup.repository.ITeacherRepository;
import com.PointLookup.service.role.IRoleService;
import com.PointLookup.service.student.IStudentService;
import com.PointLookup.service.teacher.ITeacherService;
import com.PointLookup.util.ConverterUtil;

import net.bytebuddy.utility.RandomString;

@Service
public class AuthService implements IAuthService {
	
	@Autowired
	private IAuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	
	@Autowired
    private JavaMailSender mailSender;
	
	private ConverterUtil<PersonDTO, PersonEntity> personConvert = new ConverterUtil<PersonDTO, PersonEntity>(PersonDTO.class, PersonEntity.class);
	
	private IRoleRepository roleRepo;
	
	private IStudentRepository studentRepo;
	
	private ITeacherRepository teacherRepo;
	
	@Autowired
	public AuthService(IStudentRepository studentRepo, ITeacherRepository teacherRepo,IRoleRepository roleRepo) {
		this.studentRepo = studentRepo;
		this.teacherRepo = teacherRepo;
		this.roleRepo = roleRepo;
	}
	
	@Override
	@Transactional
	public boolean RegisterUser(PersonDTO personDto, String role) {
		try {
			PersonEntity person = personConvert.toEntity(personDto);
			
			if(role == "STUDENT") {
				StudentEntity studentFind = studentRepo.findByStudentCode(personDto.getStudentCode());
				if(studentFind == null) {
					StudentEntity newStudent = new StudentEntity();
					newStudent.setStudentCode(personDto.getStudentCode());
					newStudent.setPerson(person);
					StudentEntity studentInserted = studentRepo.save(newStudent);
					person.setStudent(studentInserted);
				}else {
					return false;
				}
			}else if(role == "TEACHER") {
				TeacherEntity teacherFind = teacherRepo.findByTeacherCode(personDto.getTeacherCode());
				if(teacherFind == null) {					
					TeacherEntity newTeacher = new TeacherEntity();
					newTeacher.setTeacherCode(personDto.getTeacherCode());
					newTeacher.setPerson(person);
					TeacherEntity teacherInserted = teacherRepo.save(newTeacher);
					person.setTeacher(teacherInserted);
				}else {
					return false;
				}
			}
			
			person.setPassWord(passwordEncoder.encode(personDto.getPassWord()));
			
			RoleEntity personRole = roleRepo.findByRoleCode(role);
			
			personRole.getPersons().add(person);
			
			person.getRoles().add(personRole);
			
			person.setStatus(1);
			
			person.setVerifyCode(RandomString.make(64));
			
			PersonEntity personInsertSuccess = authRepository.save(person);
		
			if(personInsertSuccess == null) {
				return false;
			}
			
			boolean isSendEmailVerify = sendMailToVerify(person);
			
			if(!isSendEmailVerify) return false;
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}


	@Override
	public PersonEntity findOneByVerifyCode(String verifyCode) {
		return authRepository.findOneByVerifyCode(verifyCode);
	}


	@Override
	public PersonEntity findById(Long id) {
		return authRepository.findById(id).get();
	}


	@Override
	public PersonEntity findOneByUserNameAndStatus(String username, int status) {
		return authRepository.findOneByUserNameAndStatus(username, status);
	}
	
	@Transactional
	@Override
	public boolean sendMailToVerify(PersonEntity person){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String toAddress = person.getEmail();
	    String fromAddress = "quocthang1100@gmail.com";
	    String senderName = "Point-Lookup";
	    String subject = "Verify Your Registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Point-Lookup Team.";
	    content = content.replace("[[name]]", person.getFullName());
	    String verifyURL = "http://localhost:3000" + "/verify?execution=VERIFY_EMAIL&code=" + person.getVerifyCode();     
	    content = content.replace("[[URL]]", verifyURL);	    
	    try {
	    	helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);  
		    helper.setText(content, true);
	    }catch(Exception e) {
	    	return false;
	    }
		mailSender.send(message);
		return true;
	}


	@Override
	public PersonEntity UpdateOrInsertUser(PersonEntity person) {
		return authRepository.save(person);
	}
}
