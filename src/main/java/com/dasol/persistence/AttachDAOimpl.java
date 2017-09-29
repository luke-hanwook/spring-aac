package com.dasol.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dasol.domain.AttachVO;
import com.dasol.domain.Criteria;

@Repository
public class AttachDAOimpl implements AttachDAO {

	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.dasol.mapper.AttachMapper";
	
	@Override
	public List<AttachVO> getAttachList(int cno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cno", cno);
		paramMap.put("cri", cri);
		return session.selectList(namespace+".getAttach", paramMap);
	}

	@Override
	public void addAttach(AttachVO vo) throws Exception {
		session.insert(namespace+".insertAttach", vo);
	}

	@Override
	public int countAttach(int cno) throws Exception {
		return session.selectOne(namespace+".countAttach", cno);
	}

	@Override
	public void removeAttach(int ano) throws Exception {
		session.delete(namespace+".deleteAttach", ano);
	}

}
