package com.dasol.service;

import java.util.List;

import com.dasol.domain.Criteria;
import com.dasol.domain.ScrapVO;

public interface ScrapService {
	public List<ScrapVO> getScrapList(String email, Criteria cri) throws Exception;
	public void addScrap(ScrapVO vo) throws Exception;
	public ScrapVO getByCno(ScrapVO vo) throws Exception;
	public void deleteScrap(int sno) throws Exception;
	public int countScrap(String email) throws Exception;
}
