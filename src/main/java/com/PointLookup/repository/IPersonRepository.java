package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.PersonEntity;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long>{
	void deleteByUserName(String userName);
	List<PersonEntity> findByStatus(int status);
	PersonEntity findByEmail(String email);
}
