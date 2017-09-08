package com.dasol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasol.domain.CampingApiVO;
import com.dasol.domain.Criteria;
import com.dasol.domain.SearchCriteria;
import com.dasol.persistence.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDAO dao;
	
	@Override
	public List<CampingApiVO> selectList(Criteria cri) throws Exception {
		return dao.selectList(cri);
	}

	@Override
	public int getTotCount() throws Exception {
		return dao.getTotCount();
	}

	@Override
	public List<CampingApiVO> searchList(SearchCriteria scri) throws Exception {
		return dao.searchList(scri);
	}

	@Override
	public int getSearchTotCount(SearchCriteria scri) throws Exception {
		return dao.getSearchTotCount(scri);
	}

	@Override
	public List<String> getSelectCity() throws Exception {
		return dao.getCityName();
	}

	@Override
	public List<String> getSelectClassify() throws Exception {
		return dao.getClassifyName();
	}

	@Override
	public CampingApiVO getCampingInfo(String name) throws Exception {
		return dao.getCampingInfo(name);
	}
	
}
