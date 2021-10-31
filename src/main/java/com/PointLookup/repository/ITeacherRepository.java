package com.PointLookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.TeacherEntity;

@Repository
public interface ITeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
