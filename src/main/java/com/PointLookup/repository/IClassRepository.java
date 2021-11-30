package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.ClassEntity;

@Repository
public interface IClassRepository extends JpaRepository<ClassEntity, Long>{
	List<ClassEntity> findByMajor_Id(Long majorId);
	
	ClassEntity findByHomeRoomTeacher_Id(Long teacherId);
	
	ClassEntity findByClassCode(String classCode);
	
}
