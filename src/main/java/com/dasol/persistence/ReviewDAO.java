package com.dasol.persistence;

import java.util.List;

import com.dasol.domain.CampingApiVO;
import com.dasol.domain.Criteria;
import com.dasol.domain.SearchCriteria;

public interface ReviewDAO {
	public List<CampingApiVO> selectList(Criteria cri) throws Exception;
	public int getTotCount() throws Exception;
	public List<CampingApiVO> searchList(SearchCriteria scri) throws Exception;
	public int getSearchTotCount(SearchCriteria scri) throws Exception;
	public List<String> getCityName() throws Exception;
	public List<String> getClassifyName() throws Exception;
	public CampingApiVO getCampingInfo(String name) throws Exception;
}
