package com.dasol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasol.domain.CampingApiVO;
import com.dasol.persistence.CampingApiDAO;

@Service
public class CampingApiServiceImpl implements CampingApiService {
	
	@Autowired
	private CampingApiDAO dao;

	@Override
	public void insertAPI(List<CampingApiVO> apiList) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("list", apiList);
		dao.insert(paramMap);
	}
	
	
}
