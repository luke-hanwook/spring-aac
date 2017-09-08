package com.dasol.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dasol.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession session;

	private static String namespace = "com.dasol.mapper.UserMapper";

	@Override
	public UserVO getUserById(String email) {
		return session.selectOne(namespace + ".getUser", email);
	}

	@Override
	public void insertMember(UserVO userVO) throws Exception {
		session.insert(namespace + ".insertMember", userVO);
	}

	@Override
	public void insertRole(UserVO userVO) throws Exception {
		session.insert(namespace + ".insertRole", userVO);
	}

}
