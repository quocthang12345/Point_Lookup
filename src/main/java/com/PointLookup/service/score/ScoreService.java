package com.PointLookup.service.score;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.ScoreDTO;
import com.PointLookup.model.entity.ScoreEntity;
import com.PointLookup.model.entity.StudentEntity;
import com.PointLookup.model.entity.SubjectEntity;
import com.PointLookup.repository.IScoreRepository;
import com.PointLookup.repository.IStudentRepository;
import com.PointLookup.repository.ISubjectRepository;
import com.PointLookup.util.ConverterUtil;

@Service
public class ScoreService implements IScoreService {

	@Autowired
	private IScoreRepository scoreRepository;
	
	@Autowired
	private ISubjectRepository subjectRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	private ConverterUtil<ScoreDTO, ScoreEntity> scoreConverter = new ConverterUtil<ScoreDTO, ScoreEntity>(ScoreDTO.class, ScoreEntity.class);
	
	

	@Override
	public ScoreEntity addScoreToSubjectOfStudent(String subjectCode, String studentCode, ScoreDTO scoreDto) {
		try {
			SubjectEntity subject = subjectRepository.findBySubjectCode(subjectCode);
			
			if(subject == null) return null;
			
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			
			if(student == null) return null;
			
			ScoreEntity score = scoreConverter.toEntity(scoreDto);
			
			score.setStudent(student);
			
			student.getScores().add(score);
			
			score.setSubject(subject);
			
			subject.getScores().add(score);
			
			ScoreEntity scoreInserted = scoreRepository.save(score);
			
			if(scoreInserted == null) return null;
			
			return scoreInserted;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ScoreEntity updateScoreToSubjectOfStudent(String subjectCode, String studentCode, ScoreDTO scoreDto) {
		try {
			ScoreEntity scoreUpdate = scoreConverter.toEntity(scoreDto);
			
			SubjectEntity subject = subjectRepository.findBySubjectCode(subjectCode);
			
			if(subject == null) return null;
			
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			
			if(student == null) return null;
			
			ScoreEntity scoreIsUpdate = scoreRepository.findByStudent_IdAndSubject_Id(student.getId(), subject.getId());
			
			if(scoreIsUpdate == null) return null;
			
			scoreConverter.merge(scoreUpdate, scoreIsUpdate);
			
			ScoreEntity scoreUpdated = scoreRepository.save(scoreIsUpdate);
			
			if(scoreUpdated == null) return null;
			
			return scoreUpdated;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}

	@Override
	public ScoreEntity findBySubjectOfStudent(String subjectCode, String studentCode) {
		try {
			
			SubjectEntity subject = subjectRepository.findBySubjectCode(subjectCode);
			
			if(subject == null) return null;
			
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			
			if(student == null) return null;
			
			ScoreEntity scoreFind = scoreRepository.findByStudent_IdAndSubject_Id(student.getId(), subject.getId());
				
			if(scoreFind == null) return null;
			
			return scoreFind;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ScoreEntity> findByAllSubjectOfStudent(String studentCode) {
		try {
			StudentEntity student = studentRepository.findByStudentCode(studentCode);
			
			if(student == null) return null;
			
			List<ScoreEntity> listScore = scoreRepository.findByStudent_Id(student.getId());
			
			if(listScore == null) return null;
			
			return listScore;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ScoreEntity> updateListScoreToSubjectOfListStudent(List<ScoreDTO> scoreDto) {
		try {
			if(scoreDto.size() <= 0) return null;
			
			List<ScoreEntity> result = new ArrayList<ScoreEntity>();
			
			scoreDto.forEach(score -> {
				ScoreEntity scoreUpdate = scoreConverter.toEntity(score);
				
				SubjectEntity subject = subjectRepository.findBySubjectCode(score.getSubjects().getSubjectCode());
				
				if(subject == null) return;
				
				StudentEntity student = studentRepository.findByStudentCode(score.getStudents().getStudentCode());
				
				if(student == null) return;
				
				ScoreEntity scoreIsUpdate = scoreRepository.findByStudent_IdAndSubject_Id(student.getId(), subject.getId());
				
				if(scoreIsUpdate == null) return;
				
				scoreConverter.merge(scoreUpdate, scoreIsUpdate);
				
				result.add(scoreIsUpdate);
			});
			
			if(result.size() <=0 ) return null;
			
			List<ScoreEntity> scores = scoreRepository.saveAll(result);
			
			return scores;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
