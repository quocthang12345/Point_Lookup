package com.PointLookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PointLookup.model.entity.StudentEntity;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Long> {

}
