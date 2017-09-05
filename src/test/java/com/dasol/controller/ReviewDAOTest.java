package com.dasol.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dasol.domain.IndexVO;
import com.dasol.persistence.ReviewDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReviewDAOTest {
	
	@Autowired
	private ReviewDAO dao;
	
	@Test
	public void getCityNameTest() throws Exception {
		List<String> list = dao.getCityName();
		for (String s : list) {
			System.out.println(s);
		}
	}
}
