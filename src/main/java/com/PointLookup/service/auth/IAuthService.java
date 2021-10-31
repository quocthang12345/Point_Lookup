package com.PointLookup.service.auth;

import com.PointLookup.model.dto.PersonDTO;
import com.PointLookup.model.entity.PersonEntity;

public interface IAuthService {
	boolean RegisterUser(PersonDTO person, String role);
	
	PersonEntity findOneByVerifyCode(String verifyCode);
	
	PersonEntity findById(Long id);
	
	PersonEntity findOneByUserNameAndStatus(String username, int status);
	
	PersonEntity UpdateOrInsertUser(PersonEntity person);
	
	boolean sendMailToVerify(PersonEntity person);
}
