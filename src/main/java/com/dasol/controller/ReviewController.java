package com.dasol.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dasol.domain.CampingApiVO;
import com.dasol.domain.PageData;
import com.dasol.domain.SearchCriteria;
import com.dasol.service.ReviewService;

@Controller
@EnableCaching
@RequestMapping("/camping")
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	private static Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		List<CampingApiVO> voList = null;
		voList = service.searchList(cri);
		PageData pageData = new PageData(service.getSearchTotCount(cri), cri);
		model.addAttribute("list", voList);
		model.addAttribute(pageData);
		
//		long start = System.currentTimeMillis(); // 수행시간 측정
		List<String> cityList = service.getSelectCity();
		List<String> classifyList = service.getSelectClassify();
//		long end = System.currentTimeMillis(); 
//		logger.info("Cache 수행시간 : "+ Long.toString(end-start));
		
		model.addAttribute("cityList", cityList);
		model.addAttribute("classifyList", classifyList);

	}

//	@RequestMapping(value="/list/{name}", method = RequestMethod.GET)
//	public ResponseEntity<CampingApiVO> info(@PathVariable String name) throws Exception {
//		ResponseEntity<CampingApiVO> entity = null;
//		try {
//			CampingApiVO vo = service.getCampingInfo(name);
//			entity = new ResponseEntity<CampingApiVO>(vo, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<CampingApiVO>(HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}
	
	@RequestMapping(value="/list/{name}", method = RequestMethod.GET)
	public String info(@PathVariable String name, Model model) throws Exception {
		long start = System.currentTimeMillis(); // 수행시간 측정
		CampingApiVO vo = service.getCampingInfo(name);
		long end = System.currentTimeMillis(); 
		logger.info("Cache 수행시간 : "+ Long.toString(end-start));
		
		model.addAttribute("vo", vo);
		return "/camping/info";
	}
	
}

