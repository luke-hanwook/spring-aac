package com.dasol.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dasol.domain.UserVO;

public class UserDetailsImpl extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5072939216405305102L;
	
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public UserDetailsImpl(UserVO userVO) {
		super(userVO.getEmail(), userVO.getPassword(), authorities(userVO));
		this.nick = userVO.getNick();
	}

	private static Collection<? extends GrantedAuthority> authorities(UserVO userVO) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(userVO.getRole()));
		return authorities;
	}

}
