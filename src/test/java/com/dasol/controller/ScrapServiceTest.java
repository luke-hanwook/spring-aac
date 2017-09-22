package com.dasol.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dasol.domain.ScrapVO;
import com.dasol.service.ScrapService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"
		, "file:src/main/resources/security-context.xml" })
public class ScrapServiceTest {
	@Autowired
	private ScrapService service;
	
	@Test
	public void getScrapListTest() throws Exception {
		String email = "gksdnr89@naver.com";
//		List<ScrapVO> list = service.getScrapList(email);
//		
//		for (ScrapVO vo : list) {
//			System.out.println(vo);
//		}
	}
}
