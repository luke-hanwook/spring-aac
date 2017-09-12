package com.dasol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasol.domain.Criteria;
import com.dasol.domain.ReplyVO;
import com.dasol.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO dao;
	
	@Override
	public void createReply(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer cno, Criteria cri) throws Exception {
		return dao.listPage(cno, cri);
	}

	@Override
	public int count(Integer cno) throws Exception {
		return dao.count(cno);
	}
	
	@Override
	public double avg(Integer cno) throws Exception {
		return dao.avg(cno);
	}
	
}
