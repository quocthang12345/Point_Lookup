package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.MajorEntity;

@Repository
public interface IMajorRepository extends JpaRepository<MajorEntity, Long> {
	MajorEntity findByMajorName(String majorName);
	
	void deleteByMajorCode(String majorCode);
	
	MajorEntity findByMajorCode(String majorCode);
}
