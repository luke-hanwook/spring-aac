package com.dasol.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasol.service.CampingApiService;
import com.dasol.util.ApiManager;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private CampingApiService service;
	
//	private static Logger logger = LoggerFactory.getLogger(ApiManager.class);
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ResponseEntity<String> insert() throws Exception {
		try {
			service.insertAPI(ApiManager.generateAPI());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
