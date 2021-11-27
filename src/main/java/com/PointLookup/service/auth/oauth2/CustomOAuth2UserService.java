package com.PointLookup.service.auth.oauth2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.PointLookup.exception.OAuth2AuthenticationProcessingException;
import com.PointLookup.model.entity.PersonEntity;
import com.PointLookup.model.entity.RoleEntity;
import com.PointLookup.model.resource.AuthProvider;
import com.PointLookup.model.resource.ERole;
import com.PointLookup.repository.IPersonRepository;
import com.PointLookup.service.auth.oauth2.social.OAuth2UserInfo;
import com.PointLookup.service.auth.oauth2.social.OAuth2UserInfoFactory;
import com.PointLookup.service.auth.userDetail.UserPrincipal;
import com.PointLookup.service.role.RoleService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
	
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Autowired
	private RoleService roleService;
	/* 
     * hàm loadUser sử dụng load và truy cập vào user để thêm hoặc update user trong db sau khi xác thực thành công 
     * */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest); 
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        PersonEntity person = personRepository.findByEmail(oAuth2UserInfo.getEmail());
        if(person != null) {
           // kiểm tra xem cùng một tài khoản nhưng khác provider thì log ra lỗi để người sủ dụng đúng provider để login
            if(!person.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) { 
                throw new OAuth2AuthenticationProcessingException("Looks like you are signed up with " +
                		person.getProvider() + " account. Please use your " + person.getProvider() +
                        " account to login.");
            }
            person = updateExistingUser(person, oAuth2UserInfo);
        } else {
        	person = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return (OAuth2User) UserPrincipal.create(person, oAuth2User.getAttributes()); // tạo principal trong hệ thống
    }

    private PersonEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    	PersonEntity person = new PersonEntity();
    	List<RoleEntity> roles = new ArrayList<>();
    	RoleEntity userRole = roleService.findByRoleCode(ERole.STUDENT.name());
		roles.add(userRole);
		person.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()).name());
		person.setProviderId(oAuth2UserInfo.getId());
		person.setUserName(oAuth2UserInfo.getName());
		person.setEmail(oAuth2UserInfo.getEmail());
		person.setAvatar(oAuth2UserInfo.getImageUrl());
		person.setRoles(roles);       
        return personRepository.save(person);
    }

    private PersonEntity updateExistingUser(PersonEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUserName(oAuth2UserInfo.getName());
        existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
        return personRepository.save(existingUser);
    }
}
