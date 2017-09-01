package com.dasol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasol.persistence.CampingApiDAO;

@Service
public class CampingApiServiceImpl implements CampingApiService {
	
	@Autowired
	private CampingApiDAO dao;

	@Transactional
	@Override
	public void insertAPI(Map<String, List<Object>> apiMap) throws Exception {
		dao.insertApi(apiMap.get("apiList"));
		dao.insertIdx(apiMap.get("idxList"));
	}
	
	
}
