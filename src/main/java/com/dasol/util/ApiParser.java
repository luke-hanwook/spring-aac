package com.dasol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dasol.domain.CampingApiVO;
import com.dasol.domain.IndexVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiParser {
	
	private static ApiParser apiParser = null;
	private Map<String, Object> propertyMap = null;
	
	public static ApiParser getInstance() {
		if(apiParser == null) {
			apiParser = new ApiParser();
		}
		return apiParser;
	}
	
	private ApiParser() {
		propertyMap = new HashMap<String, Object>();
		propertyMap.put("야영사이트수", "siteCnt");
		propertyMap.put("편의시설", "facilities");
		propertyMap.put("야영(캠핑)장구분", "classify");
		propertyMap.put("기타부대시설", "etc");
		propertyMap.put("주차장면수", "parkingCnt");
		propertyMap.put("이용시간", "operatingTime");
		propertyMap.put("위도", "latitude");
		propertyMap.put("1일최대수용인원수", "maxpersonCnt");
		
		propertyMap.put("이용요금", "price");
		propertyMap.put("관리기관명", "management");
		propertyMap.put("소재지지번주소", "addrNumber");
		propertyMap.put("경도", "longitude");
		propertyMap.put("건축연면적", "architectureArea");
		propertyMap.put("소재지도로명주소", "addrRoad");
		propertyMap.put("부지면적", "siteArea");
		propertyMap.put("안전시설", "safetyfacilities");
		
		propertyMap.put("야영(캠핑)장명", "name");
		propertyMap.put("관리기관전화번호", "telM");
		propertyMap.put("야영장전화번호", "telC");
		propertyMap.put("데이터기준일자", "updatedate");
	}
	
	/**
	 * 쿼리의 한글 프로퍼티를 영문 프로퍼티로 변경하는 메서드
	 * @param jsonString
	 * @return
	 */
	public String parsePropertyKorToEng(String jsonString) {
		Set<String> propertySet = propertyMap.keySet();
		Iterator<String> iter = propertySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			jsonString = jsonString.replace(key, (String)propertyMap.get(key));
		}
		return jsonString;
	}
	
	/**
	 * Json String을 가공하여 여러개로 만드는 메서드
	 * @param jsonString
	 * @return
	 */
	public static String[] campingApiParser(String jsonString) {
		String[] jsonArr = jsonString.replace("\\\"","").replace("[", "").replace("]", "").split("},");
		for (int i = 0; i < jsonArr.length; i++) {
			if(jsonArr.length-1 > i) {
				jsonArr[i] += "}";
			}
		}
		return jsonArr;
	}
	
	/**
	 * json 형태의 string을 java 객체로 만드는 메서드
	 * @param jsonArr
	 * @return
	 * @throws Exception
	 */
	private static int idx = 0;
	private static List<Object> idxList = new ArrayList<>();
	private static List<Object> apiList = new ArrayList<>();
	
	public static Map<String, List<Object>> getJsonList(String[] jsonArr) throws Exception {
		Map<String, List<Object>> apiMap = new HashMap<>();
		Map<String, Object> duplicatedMap = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		CampingApiVO apiVo = null;
		
		for (String json : jsonArr) {
			apiVo = mapper.readValue(json, CampingApiVO.class);
			
			IndexVO idxVo = new IndexVO(apiVo.getCityCode(), apiVo.getClassifyCode()
					, IndexCommons.CITY_ARR_KOR[apiVo.getCityCode()]
					, IndexCommons.CLASSIFY_ARR_KOR[apiVo.getClassifyCode()/100-1]);
			
			duplicatedMap = duplicatedIndex(duplicatedMap, idxList, idxVo); 
			if(!(boolean)duplicatedMap.get("isDuplicated")) { //index 중복 아니라면
				idxVo.setIdx_id(++idx);
				idxList.add(idxVo);
				apiVo.setIdx_id(idx);
			} else { // 중복이라면
				apiVo.setIdx_id(((IndexVO)duplicatedMap.get("duplicatedObj")).getIdx_id());
			}
			
			apiList.add(apiVo);
		}
		apiMap.put("apiList", apiList);
		apiMap.put("idxList", idxList);
		
		return apiMap;
	}
	
	private static Map<String, Object> duplicatedIndex(Map<String, Object> duplicatedMap, List<Object> idxList, IndexVO idxVo) {
		boolean isc = false;
		for (Object obj : idxList) {
			//중복체크
			if(((IndexVO)obj).equals(idxVo)) {
				isc = true;
				duplicatedMap.put("duplicatedObj", obj);
			}
		}
		duplicatedMap.put("isDuplicated", isc);
		return duplicatedMap;
	}
}
