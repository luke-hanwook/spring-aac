package com.dasol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsServiceImpl service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user_id = (String)authentication.getPrincipal();   
        String user_pw = (String)authentication.getCredentials();
          
        System.out.printf("사용자 로그인정보: %s \n", user_id + "/" + user_pw);
         
        User user = (User)service.loadUserByUsername(user_id);
        System.out.printf("사용자 DB 정보: %s \n", user.getUsername() + "/" + user.getPassword());
 
        // 화면에서 입력한 이용자의 비밀번호(평문)와 DB에서 가져온 이용자의 암호화된 비밀번호를 비교한다
        if(! passwordEncoder.matches(user_pw, user.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        
        UsernamePasswordAuthenticationToken result 
        	= new UsernamePasswordAuthenticationToken(user_id, null, user.getAuthorities());
        result.setDetails(user);
        return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
