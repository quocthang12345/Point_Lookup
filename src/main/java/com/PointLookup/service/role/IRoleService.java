package com.PointLookup.service.role;

import com.PointLookup.model.entity.RoleEntity;

public interface IRoleService {
	RoleEntity findByRoleCode(String roleName);
}
