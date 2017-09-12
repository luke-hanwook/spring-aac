package com.dasol.domain;

import java.util.Date;

public class ReplyVO {
	private Integer rno;
	private String email;
	private Integer cno;
	private String content;
	private Integer star;
	private Date regdate;
	private Date updatedate;
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", email=" + email + ", cno=" + cno + ", content=" + content + ", star=" + star
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + ", nick=" + nick + "]";
	}

}