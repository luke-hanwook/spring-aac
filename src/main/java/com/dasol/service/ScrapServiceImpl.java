package com.dasol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasol.domain.Criteria;
import com.dasol.domain.ScrapVO;
import com.dasol.persistence.ScrapDAO;

@Service
public class ScrapServiceImpl implements ScrapService {

	@Autowired
	private ScrapDAO dao;
	
	@Override
	public List<ScrapVO> getScrapList(String email, Criteria cri) throws Exception {
		return dao.getScrapAll(email, cri);
	}

	@Override
	public void addScrap(ScrapVO vo) throws Exception {
		dao.addScrap(vo);
	}

	@Override
	public ScrapVO getByCno(ScrapVO vo) throws Exception {
		return dao.getByCno(vo);
	}

	@Override
	public void deleteScrap(int sno) throws Exception {
		dao.deleteScrap(sno);
	}

	@Override
	public int countScrap(String email) throws Exception {
		return dao.countScrap(email);
	}

}
