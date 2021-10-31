package com.PointLookup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.PersonEntity;

@Repository
public interface IAuthRepository extends JpaRepository<PersonEntity, Long> {
	
	PersonEntity findOneByUserNameAndStatus(String userName,int status);
	
	Optional<PersonEntity> findByEmail(String email);
	
	PersonEntity findOneByVerifyCode(String verifyCode);	
	
}
