package com.dasol.persistence;

import java.util.List;

import com.dasol.domain.Criteria;
import com.dasol.domain.ScrapVO;

public interface ScrapDAO {
	public List<ScrapVO> getScrapAll(String email, Criteria cri) throws Exception;
	public void addScrap(ScrapVO scrapVO) throws Exception;
	public ScrapVO getByCno(ScrapVO scrapVO) throws Exception;
	public void deleteScrap(int sno) throws Exception;
	public int countScrap(String email) throws Exception;
}
