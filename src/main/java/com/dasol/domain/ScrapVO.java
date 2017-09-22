package com.dasol.domain;

import java.util.Date;

public class ScrapVO {
	private int sno;
	private String email;
	private int cno;
	private Date regdate;
	private String name;
	private String cityname;
	private String classifyname;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getClassifyname() {
		return classifyname;
	}

	public void setClassifyname(String classifyname) {
		this.classifyname = classifyname;
	}

	@Override
	public String toString() {
		return "ScrapVO [sno=" + sno + ", email=" + email + ", cno=" + cno + ", regdate=" + regdate + ", name=" + name
				+ ", cityname=" + cityname + ", classifyname=" + classifyname + "]";
	}

}
