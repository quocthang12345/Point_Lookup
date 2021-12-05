package com.PointLookup.service.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.repository.IAuthRepository;
import com.PointLookup.repository.IPersonRepository;
import com.PointLookup.repository.IRoleRepository;
import com.PointLookup.service.auth.tokenProvider.JwtTokenProvider;
import com.PointLookup.util.ConverterUtil;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonRepository personRepo;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private IAuthRepository authRepo;
	
	private ConverterUtil<PersonDTO, PersonEntity> personConvert = new ConverterUtil<PersonDTO, PersonEntity>(PersonDTO.class, PersonEntity.class);
	
	@Override
	public List<PersonEntity> findAll(){
		return personRepo.findAll();
	}	

	@Transactional
	@Override
	public boolean deleteUserByUserName(String username) {
		try {
			personRepo.deleteByUserName(username);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	@Override
	public boolean updatePerson(PersonDTO personDto) {
		try {
			PersonEntity personExist = authRepo.findOneByUserNameAndStatus(personDto.getUserName(), 1);
			if(personExist == null) {
				return false;
			}
			PersonEntity personUpdate = personConvert.toEntity(personDto);
			
			personConvert.merge(personUpdate,personExist);
			
			personRepo.save(personExist);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<PersonEntity> findByStatus(int status) {
		try {
			List<PersonEntity> listPerson = personRepo.findByStatus(status);
			if(listPerson == null) {
				return null;
			}
			return listPerson;			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PersonEntity findPersonByToken(HttpServletRequest request) {
		try {
			if(request == null) return null;
			
			String token = jwtTokenProvider.getJwtFromRequest(request);
			
			if(token == null) return null;
			
			String username = jwtTokenProvider.getUsernameFromJWT(token);
			
			PersonEntity person = personRepo.findByUserName(username);
			
			return person;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PersonEntity findPersonByUsername(String username) {
		try {
			if(username == null) return null;
			
			PersonEntity person = personRepo.findByUserName(username);
			
			return person;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PersonEntity> findListPersonByRole(String roleCode) {
		if(roleCode == null) return null;
		
		RoleEntity role = roleRepository.findByRoleCode(roleCode);
		
		if(role == null) return null;
		
		List<PersonEntity> result = role.getPersons();
		
		return result;
	}
}
