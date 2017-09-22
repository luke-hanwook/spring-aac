package com.dasol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasol.domain.Criteria;
import com.dasol.domain.ScrapVO;
import com.dasol.service.ScrapService;

@RestController
@RequestMapping("/scrap")
public class ScrapController {
	
	@Autowired
	private ScrapService service;
	
	@RequestMapping("/{page}")
	public ResponseEntity<Map<String, Object>> getScrap(@PathVariable Integer page, Authentication authentication) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			String email = authentication.getName();
			List<ScrapVO> list = service.getScrapList(email, cri);
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			map.put("scnt", service.countScrap(email));
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> addScrap(@RequestBody ScrapVO vo) {
		ResponseEntity<String> entity = null;
		try {
			ScrapVO getvo = service.getByCno(vo);
			
			if(getvo != null) { // 중복이라면
				entity = new ResponseEntity<>("duplicated", HttpStatus.OK);
			} else {
				service.addScrap(vo);
				entity = new ResponseEntity<>("success", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{sno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteScrap(@PathVariable int sno) {
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteScrap(sno);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
