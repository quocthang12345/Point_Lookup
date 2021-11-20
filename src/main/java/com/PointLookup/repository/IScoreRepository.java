package com.PointLookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.ScoreEntity;

@Repository
public interface IScoreRepository extends JpaRepository<ScoreEntity, Long>{
	ScoreEntity findByStudent_IdAndSubject_Id(Long student_id, Long subject_id);
	
	List<ScoreEntity> findByStudent_Id(Long student_id);
}
