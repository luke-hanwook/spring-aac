package com.dasol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasol.domain.Criteria;
import com.dasol.domain.ReplyVO;
import com.dasol.service.ReplyService;

@RestController
@RequestMapping("/review")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertReply(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.createReply(vo);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/{rno}")
	public ResponseEntity<String> deleteReply(@PathVariable Integer rno) {
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable Integer rno, @RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{cno}/{page}")
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("cno") Integer cno, @PathVariable Integer page) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			Map<String, Object> map = new HashMap<>();
			List<ReplyVO> list = service.listReplyPage(cno, cri);
			map.put("list", list);
			
			map.put("avg", service.avg(cno));
			map.put("cnt", service.count(cno));
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
