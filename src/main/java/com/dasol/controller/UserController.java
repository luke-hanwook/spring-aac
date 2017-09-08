package com.dasol.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dasol.domain.UserVO;
import com.dasol.security.UserDetailsImpl;
import com.dasol.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET() throws Exception {
		
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
		
		Map<String, Object> errorsMap = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
            for (FieldError e : result.getFieldErrors()) {
            	errorsMap.put(e.getField(), e.getDefaultMessage());
            }
            throw new Exception();
		}
		
		userVO.setRole("ROLE_USER");
		userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
		service.joinUser(userVO);
		
		User user = new UserDetailsImpl(userVO);
		UsernamePasswordAuthenticationToken auth 
			= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		auth.setDetails(user);
		SecurityContextHolder.getContext().setAuthentication(auth);
			
		return "redirect:/";
	}
	
	@RequestMapping(value = "/join/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> checkId(@PathVariable String email) {
		ResponseEntity<String> entity = null;
		
		UserVO vo = service.getUserById(email);
		
		if(vo != null) {
			entity = new ResponseEntity<>("duplicated", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		}
		
		return entity;
	}
}
