package com.dasol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasol.domain.UserVO;
import com.dasol.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserVO getUserById(String email) {
		return dao.getUserById(email);
	}

	@Transactional
	@Override
	public void joinUser(UserVO userVO) throws Exception {
		dao.insertMember(userVO);
		dao.insertRole(userVO);
	}
	
}
