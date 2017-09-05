package com.dasol.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.dasol.domain.CampingApiVO;
import com.dasol.domain.Criteria;
import com.dasol.domain.SearchCriteria;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.dasol.mapper.ReviewdMapper";
	
	@Override
	public List<CampingApiVO> selectList(Criteria cri) throws Exception {
		return session.selectList(namespace+".list", cri);
	}

	@Override
	public int getTotCount() throws Exception {
		return session.selectOne(namespace+".count");
	}

	@Override
	public List<CampingApiVO> searchList(SearchCriteria scri) throws Exception {
		return session.selectList(namespace+".search", scri);
	}

	@Override
	public int getSearchTotCount(SearchCriteria scri) throws Exception {
		return session.selectOne(namespace+".searchCount", scri);
	}
	
	@Cacheable(value="getCityNameCache")
	@Override
	public List<String> getCityName() throws Exception {
		return session.selectList(namespace+".getCityName");
	}
	
	@Cacheable(value="getClassifyNameCache")
	@Override
	public List<String> getClassifyName() throws Exception {
		return session.selectList(namespace+".getClassifyName");
	}
	
	
}
