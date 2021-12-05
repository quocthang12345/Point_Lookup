package com.PointLookup.service.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;

public interface IPersonService {
	List<PersonEntity> findAll();
	boolean deleteUserByUserName(String username);
	boolean updatePerson(PersonDTO personDto);
	List<PersonEntity> findByStatus(int status);
	PersonEntity findPersonByToken(HttpServletRequest request);
	PersonEntity findPersonByUsername(String username);
	List<PersonEntity> findListPersonByRole(String roleCode);
}
