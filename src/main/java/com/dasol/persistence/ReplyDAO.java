package com.dasol.persistence;

import java.util.List;

import com.dasol.domain.Criteria;
import com.dasol.domain.ReplyVO;

public interface ReplyDAO {

	public void create(ReplyVO vo) throws Exception;

	public void update(ReplyVO vo) throws Exception;

	public void delete(Integer rno) throws Exception;

	public List<ReplyVO> listPage(Integer cno, Criteria cri) throws Exception;

	public int count(Integer cno) throws Exception;
	
	public double avg(Integer cno) throws Exception;
}
