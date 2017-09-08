package com.dasol.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dasol.domain.UserVO;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 
	
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public void login(@RequestParam(value="error", required=false) String error, Model model) {
		model.addAttribute("error", error); // Sha 암호값을 보기 위한 테스트용. 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info(auth.getName() + "//" );
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}

}
