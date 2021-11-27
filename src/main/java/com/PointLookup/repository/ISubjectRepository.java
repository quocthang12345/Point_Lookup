package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.SubjectEntity;

@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
	SubjectEntity findBySubjectCode(String subjectCode);
	
	List<SubjectEntity> findByStudent_Id(Long id);
}
