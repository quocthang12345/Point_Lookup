package com.PointLookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PointLookup.model.entity.SubjectEntity;

public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {
 
}
