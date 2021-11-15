package com.PointLookup.service.major;

import java.util.List;

import com.PointLookup.model.dto.MajorDTO;
import com.PointLookup.model.entity.MajorEntity;

public interface IMajorService {
	MajorEntity addOrUpdateMajor(MajorDTO major);
	
	List<MajorEntity> findListMajorByMajorName(String majorName);
	
	boolean deleteByMajorCode(String majorCode);
	
	MajorEntity findByMajorCode(String majorCode);
}
