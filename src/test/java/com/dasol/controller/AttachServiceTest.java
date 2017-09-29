package com.dasol.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dasol.domain.AttachVO;
import com.dasol.domain.Criteria;
import com.dasol.service.AttachService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"
		, "file:src/main/resources/security-context.xml" })
public class AttachServiceTest {
	@Autowired
	private AttachService service;
	
	@Test
	public void getAttachListTest() throws Exception {
		int cno = 2;
		Criteria cri = new Criteria();
		cri.setPage(0);
		cri.setPerPageNum(10);
		
		List<AttachVO> list = service.getAttachList(cno, cri);
		
		for (AttachVO vo : list) {
			System.out.println(vo);
		}
	}
}
