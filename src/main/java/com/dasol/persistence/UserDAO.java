package com.dasol.persistence;

import com.dasol.domain.UserVO;

public interface UserDAO {
	public UserVO getUserById(String email);
	public void insertMember(UserVO userVO) throws Exception;
	public void insertRole(UserVO userVO) throws Exception;
}
