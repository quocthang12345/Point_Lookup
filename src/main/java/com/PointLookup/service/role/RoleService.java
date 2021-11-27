package com.PointLookup.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.repository.IRoleRepository;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public RoleEntity findByRoleCode(String roleCode) {
		return roleRepository.findByRoleCode(roleCode);
	}
}
