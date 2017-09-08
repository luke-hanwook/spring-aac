package com.dasol.service;

import com.dasol.domain.UserVO;

public interface UserService {
	public UserVO getUserById(String email) ;
	public void joinUser(UserVO userVO) throws Exception;
}
