package com.dasol.domain;

public class IndexVO {
	private int idx_id;
	private int citycode;
	private String cityname;
	private int classifycode;
	private String classifyname;
	
	public IndexVO(int citycode, int classifycode, String cityname, String classifyname) {
		this.citycode = citycode;
		this.classifycode = classifycode;
		this.cityname = cityname;
		this.classifyname = classifyname;
	}
	
	public IndexVO(int idx_id, int citycode, int classifycode) {
		this.idx_id = idx_id;
		this.citycode = citycode;
		this.classifycode = classifycode;
	}

	public int getIdx_id() {
		return idx_id;
	}

	public void setIdx_id(int idx_id) {
		this.idx_id = idx_id;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getClassifycode() {
		return classifycode;
	}

	public void setClassifycode(int classifycode) {
		this.classifycode = classifycode;
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
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		
		return this.citycode == ((IndexVO)obj).getCitycode() 
				&& this.classifycode == ((IndexVO)obj).getClassifycode();
	}
	
	@Override
	public String toString() {
		return "IndexVO [idx_id=" + idx_id + ", citycode=" + citycode + ", classifycode=" + classifycode + "]";
	}

}
