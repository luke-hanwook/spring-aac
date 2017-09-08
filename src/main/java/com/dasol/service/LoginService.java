package com.dasol.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dasol.domain.UserVO;

public class LoginService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		System.out.println("uservo=");
		
		UserVO userVO = userService.getUserById(email);
		System.out.println("uservo=" + userVO);
		
		if (userVO == null) {
			throw new UsernameNotFoundException("No user found with email = " + userVO.getEmail());
		}

		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		UserDetails user = new User(email, userVO.getPassword(), roles);

		return user;

	}

}
