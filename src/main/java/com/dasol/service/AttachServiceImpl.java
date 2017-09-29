package com.dasol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasol.domain.AttachVO;
import com.dasol.domain.Criteria;
import com.dasol.persistence.AttachDAO;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	private AttachDAO dao;
	
	@Override
	public List<AttachVO> getAttachList(int cno, Criteria cri) throws Exception {
		return dao.getAttachList(cno, cri);
	}

	@Override
	public void addAttach(AttachVO vo) throws Exception {
		dao.addAttach(vo);
	}

	@Override
	public int countAttach(int cno) throws Exception {
		return dao.countAttach(cno);
	}

	@Override
	public void removeAttach(int ano) throws Exception {
		dao.removeAttach(ano);
	}
	
}
