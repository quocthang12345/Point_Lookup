package com.PointLookup.service.auth.userDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PointLookup.model.dto.MyUserDetail;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.repository.IAuthRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private IAuthRepository authRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PersonEntity person = authRepository.findOneByUserNameAndStatus(username, 1);
		if(person == null) {
			throw new UsernameNotFoundException(" Username or Password not correct! ");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role: person.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
		}
		MyUserDetail myUser = new MyUserDetail(person.getUserName(),person.getPassWord(), 
							true, true, true, true, authorities);
		myUser.setFullName(person.getFullName());
		return myUser;
	}
	
	
}
