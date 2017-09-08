package com.dasol.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dasol.domain.UserVO;
import com.dasol.persistence.UserDAO;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserVO userVO = userDAO.getUserById(email);
		if(userVO == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserDetailsImpl(userVO);
	}

}