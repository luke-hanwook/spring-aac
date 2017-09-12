package com.dasol.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dasol.domain.Criteria;
import com.dasol.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession session;

	private static String namespace = "com.dasol.mapper.ReplyMapper";

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer cno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cno", cno);
		paramMap.put("cri", cri);
		return session.selectList(namespace + ".getList", paramMap);
	}

	@Override
	public int count(Integer cno) throws Exception {
		return session.selectOne(namespace + ".count", cno);
	}

	@Override
	public double avg(Integer cno) throws Exception {
		return session.selectOne(namespace + ".avg", cno);
	}
}
