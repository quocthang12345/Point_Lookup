package com.PointLookup.service.score;

import java.util.List;

import com.PointLookup.model.dto.ScoreDTO;
import com.PointLookup.model.entity.ScoreEntity;

public interface IScoreService {
	ScoreEntity addScoreToSubjectOfStudent(String subjectCode, String studentCode, ScoreDTO score);
	
	ScoreEntity updateScoreToSubjectOfStudent(String subjectCode, String studentCode, ScoreDTO score);
	
	ScoreEntity findBySubjectOfStudent(String subjectCode, String studentCode);
	
	List<ScoreEntity> findByAllSubjectOfStudent(String studentCode);

	List<ScoreEntity> updateListScoreToSubjectOfListStudent(List<ScoreDTO> scoreDto);
}
