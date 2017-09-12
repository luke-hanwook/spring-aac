package com.dasol.service;

import java.util.List;
import java.util.Map;

import com.dasol.domain.Criteria;
import com.dasol.domain.ReplyVO;

public interface ReplyService {
	public void createReply(ReplyVO vo) throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;

	public void removeReply(Integer rno) throws Exception;

	public List<ReplyVO> listReplyPage(Integer cno, Criteria cri) throws Exception;

	public int count(Integer cno) throws Exception;
	
	public double avg(Integer cno) throws Exception;
}
