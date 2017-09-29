package com.dasol.persistence;

import java.util.List;

import com.dasol.domain.AttachVO;
import com.dasol.domain.Criteria;

public interface AttachDAO {
	public List<AttachVO> getAttachList(int cno, Criteria cri) throws Exception;
	public void addAttach(AttachVO vo) throws Exception;
	public int countAttach(int cno) throws Exception;
	public void removeAttach(int ano) throws Exception;
}
