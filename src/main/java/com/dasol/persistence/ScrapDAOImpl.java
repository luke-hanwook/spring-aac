package com.dasol.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dasol.domain.Criteria;
import com.dasol.domain.ScrapVO;

@Repository
public class ScrapDAOImpl implements ScrapDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.dasol.mapper.ScrapMapper";
	
	@Override
	public List<ScrapVO> getScrapAll(String email, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		paramMap.put("cri", cri);
		return sqlSession.selectList(namespace+".getScrapAll", paramMap);
	}

	@Override
	public void addScrap(ScrapVO scrapVO) throws Exception {
		sqlSession.selectList(namespace+".insertScrap", scrapVO);
	}

	@Override
	public ScrapVO getByCno(ScrapVO scrapVO) throws Exception {
		return sqlSession.selectOne(namespace+".getByCno", scrapVO);
	}

	@Override
	public void deleteScrap(int sno) throws Exception {
		sqlSession.delete(namespace+".deleteScrap", sno);
	}

	@Override
	public int countScrap(String email) throws Exception {
		return sqlSession.selectOne(namespace+".countScrap", email);
	}

}
