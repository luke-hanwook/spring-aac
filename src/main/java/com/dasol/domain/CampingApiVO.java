package com.dasol.domain;

import com.dasol.util.IndexCommons;

public class CampingApiVO {
	private String _id;
	private String name;
	private String addrNumber;
	private String addrRoad;
	private String price;
	private String classify;
	private String facilities;
	private String safetyfacilities;
	private String operatingTime;
	private String management;
	private String telM;
	private String telC;
	private String architectureArea;
	private String siteArea;
	private String siteCnt;
	private String parkingCnt;
	private String longitude;
	private String latitude;
	private String maxpersonCnt;
	private String etc;
	private String updatedate;
	private int idx_id;
	private String cityname;
	private String classifyname;

	/**
	 * index 생성 위한 city 코드 반환
	 * @return
	 */
	public int getCityCode() {
		int citycode = 0;
		for (int i = 1; i < IndexCommons.CITY_ARR_KOR.length; i++) {
			if(addrNumber != null && addrNumber.indexOf(IndexCommons.CITY_ARR_KOR[i])  == 0 || // 지번 주소가 더 많다.
					addrRoad != null && addrRoad.indexOf(IndexCommons.CITY_ARR_KOR[i]) == 0) {
				citycode = i; // 1,2,3 ...
			}
		}
		return citycode;
	}
	
	/**
	 * index 생성 위한 classify 코드 반환
	 * @return
	 */
	public int getClassifyCode() {
		int classifycode = 0;
		// 코드 줄이기..
		if(classify != null && classify.contains(IndexCommons.CAR_CAMPING_KOR)|| 
				name != null && name.contains(IndexCommons.AUTO_CAMPING_KOR)) {
			classifycode = IndexCommons.AUTO_CAMPING;
		} else if (name != null && name.contains(IndexCommons.GLAMPING_KOR)) {
			classifycode = IndexCommons.GLAMPING;
		} else if (name != null && name.contains(IndexCommons.CARAVAN_KOR)) {
			classifycode = IndexCommons.CARAVAN;
		} else {
			classifycode = IndexCommons.GENERAL_CAMPING;
		}
		
		return classifycode;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddrNumber() {
		return addrNumber;
	}

	public void setAddrNumber(String addrNumber) {
		this.addrNumber = addrNumber;
	}

	public String getAddrRoad() {
		return addrRoad;
	}

	public void setAddrRoad(String addrRoad) {
		this.addrRoad = addrRoad;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getSafetyfacilities() {
		return safetyfacilities;
	}

	public void setSafetyfacilities(String safetyfacilities) {
		this.safetyfacilities = safetyfacilities;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getTelM() {
		return telM;
	}

	public void setTelM(String telM) {
		this.telM = telM;
	}

	public String getTelC() {
		return telC;
	}

	public void setTelC(String telC) {
		this.telC = telC;
	}

	public String getArchitectureArea() {
		return architectureArea;
	}

	public void setArchitectureArea(String architectureArea) {
		this.architectureArea = architectureArea;
	}

	public String getSiteArea() {
		return siteArea;
	}

	public void setSiteArea(String siteArea) {
		this.siteArea = siteArea;
	}

	public String getSiteCnt() {
		return siteCnt;
	}

	public void setSiteCnt(String siteCnt) {
		this.siteCnt = siteCnt;
	}

	public String getParkingCnt() {
		return parkingCnt;
	}

	public void setParkingCnt(String parkingCnt) {
		this.parkingCnt = parkingCnt;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMaxpersonCnt() {
		return maxpersonCnt;
	}

	public void setMaxpersonCnt(String maxpersonCnt) {
		this.maxpersonCnt = maxpersonCnt;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public int getIdx_id() {
		return idx_id;
	}

	public void setIdx_id(int idx_id) {
		this.idx_id = idx_id;
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
		return "CampingApiVO [_id=" + _id + ", name=" + name + ", addrNumber=" + addrNumber + ", addrRoad=" + addrRoad
				+ ", price=" + price + ", classify=" + classify + ", facilities=" + facilities + ", safetyfacilities="
				+ safetyfacilities + ", operatingTime=" + operatingTime + ", management=" + management + ", telM="
				+ telM + ", telC=" + telC + ", architectureArea=" + architectureArea + ", siteArea=" + siteArea
				+ ", siteCnt=" + siteCnt + ", parkingCnt=" + parkingCnt + ", longitude=" + longitude + ", latitude="
				+ latitude + ", maxpersonCnt=" + maxpersonCnt + ", etc=" + etc + ", updatedate=" + updatedate
				+ ", idx_id=" + idx_id + ", cityname=" + cityname + ", classifyname=" + classifyname + "]";
	}

}
