package com.dasol.domain;

import java.util.Date;

public class AttachVO {
	private int ano;
	private String email;
	private int cno;
	private String fullpath;
	private Date regdate;
	
	public AttachVO() {
		super();
	}

	public AttachVO(String email, int cno, String fullpath) {
		super();
		this.email = email;
		this.cno = cno;
		this.fullpath = fullpath;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "AttachVO [ano=" + ano + ", email=" + email + ", cno=" + cno + ", fullpath=" + fullpath + ", regdate="
				+ regdate + "]";
	}

}
