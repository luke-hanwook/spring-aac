package com.dasol.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CampingApiDAOImpl implements CampingApiDAO {

	@Autowired
	private SqlSession session;

	private static String namespace = "com.dasol.mapper.ApiMapper";

	@Override
	public void insert(Map<String, Object> paramMap) throws Exception {
		session.insert(namespace + ".insert", paramMap);
	}

}
