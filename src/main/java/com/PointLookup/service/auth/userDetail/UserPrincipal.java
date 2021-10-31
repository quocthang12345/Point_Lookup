package com.PointLookup.service.auth.userDetail;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.PointLookup.model.entity.PersonEntity;


public class UserPrincipal implements UserDetails  {
	private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(PersonEntity person) {
        List<SimpleGrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"));

        return new UserPrincipal(
        		person.getId(),
        		person.getEmail(),
        		person.getPassWord(),
                authorities
        );
    }

    public static UserPrincipal create(PersonEntity person, Map<String, Object> attributes) {
        UserPrincipal personPrincipal = UserPrincipal.create(person);
        personPrincipal.setAttributes(attributes);
        return personPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

//    @Override
    public String getName() {
        return String.valueOf(id);
    }

}
