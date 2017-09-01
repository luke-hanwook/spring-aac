package com.dasol.controller;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dasol.util.ApiManager;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class APITest {
	private static Logger logger = LoggerFactory.getLogger(ApiManager.class);
	
	@Inject
	private WebApplicationContext wac;
	
	private MockMvc mockMvc; // 브라우저에서 요청과 응답을 의미하는 객체
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 가상의 요청과 응답을 처리하기 위해 MockMvc객체를 생성한다.
		logger.info("setup............");
	}
	
	@Test
	public void testApi() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/insert")); // get, post
	}
}
