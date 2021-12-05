package com.PointLookup.service.major;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Transactional
	@Override
	public MajorEntity addMajor(MajorDTO majorDTO) {
		try {
			MajorEntity major = majorConverter.toEntity(majorDTO);
			
			if(major == null) return null;
			
			MajorEntity majorSuccess = majorRepository.save(major);
			
			return majorSuccess;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public MajorEntity updateMajor(MajorDTO majorDto) {
		try {
			MajorEntity majorExist = majorRepository.findByMajorCode(majorDto.getMajorCode());
			
			if(majorExist == null) return null;
			
			majorExist.setMajorName(majorDto.getMajorName());
			
			MajorEntity major = majorRepository.save(majorExist);
			
			return major;
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

	@Transactional
	@Override
	public boolean deleteByMajorCode(String majorCode) {
		try {
			if(majorCode == null) return false;
			
			majorRepository.deleteByMajorCode(majorCode);
			
			return true;
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
