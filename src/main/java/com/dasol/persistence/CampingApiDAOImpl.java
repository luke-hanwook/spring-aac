package com.dasol.persistence;

import java.util.List;
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
	public void insertApi(List<Object> list) throws Exception {
		session.insert(namespace + ".insertApi", list);
	}
	
	@Override
	public void insertIdx(List<Object> list) throws Exception {
		session.insert(namespace + ".insertIdx", list);
	}

}
