package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.ClassEntity;
import com.PointLookup.model.entity.StudentEntity;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Long> {
	StudentEntity findByStudentCode(String studentCode);

	boolean deleteByStudentCode(String studentCode);
	
}
