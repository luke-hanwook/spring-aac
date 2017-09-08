package com.dasol.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserVO {

	@NotNull
	@Email(message="이메일 형식이 올바르지 않습니다.")
	private String email;
	@NotNull
	@Pattern(regexp="^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$", message="비밀번호 형식이 올바르지 않습니다.")
	private String password;
	private String role;
	@NotNull
	@Size(min = 1, max = 20, message = "닉네임 형식이 올바르지 않습니다.")
	private String nick;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "UserVO [email=" + email + ", password=" + password + "]";
	}

}
