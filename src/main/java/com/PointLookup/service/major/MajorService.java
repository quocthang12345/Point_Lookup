package com.PointLookup.service.major;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.MajorDTO;
import com.PointLookup.model.entity.MajorEntity;
import com.PointLookup.repository.IMajorRepository;
import com.PointLookup.util.ConverterUtil;

@Service
public class MajorService implements IMajorService {
	
	private ConverterUtil<MajorDTO, MajorEntity> majorConverter = new ConverterUtil<MajorDTO, MajorEntity>(MajorDTO.class, MajorEntity.class);
	
	@Autowired
	private IMajorRepository majorRepository;
	
	@Override
	public MajorEntity addOrUpdateMajor(MajorDTO majorDTO) {
		try {
			MajorEntity major = majorConverter.toEntity(majorDTO);
			
			if(major == null) return null;
			
			if(major.getId() != null) {
				Optional<MajorEntity> majorExists = majorRepository.findById(major.getId());
				if(majorExists.isPresent()) {
					majorConverter.merge(major, majorExists);
				}
			}
			MajorEntity majorSuccess = majorRepository.save(major);
			return majorSuccess;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<MajorEntity> findListMajorByMajorName(String majorName) {
		try {
			List<MajorEntity> listMajor = new ArrayList<MajorEntity>();
			if(majorName != null) {
				MajorEntity	major = majorRepository.findByMajorName(majorName);
				listMajor.add(major);
			}else {
				listMajor = majorRepository.findAll();
			}
			return listMajor;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteByMajorCode(String majorCode) {
		try {
			if(majorCode == null) return false;
			boolean isDeleted = majorRepository.deleteByMajorCode(majorCode);
			return isDeleted;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MajorEntity findByMajorCode(String majorCode) {
		try {
			if(majorCode == null) return null;
			MajorEntity major = majorRepository.findByMajorCode(majorCode);
			return major;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
