package com.dasol.domain;

public class IndexVO {
	private int _id;
	private int citycode;
	private int classifycode;
	
	public IndexVO(int _id, int citycode, int classifycode) {
		this._id = _id;
		this.citycode = citycode;
		this.classifycode = classifycode;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
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

	@Override
	public String toString() {
		return "indexVO [_id=" + _id + ", citycode=" + citycode + ", classifycode=" + classifycode + "]";
	}

}
