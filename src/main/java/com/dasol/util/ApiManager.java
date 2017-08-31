package com.dasol.util;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;

public class ApiManager {
	
	private static final int IDX_JUMP = 1000;
	
	public static String runAPI(Integer idx) throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.data.go.kr/openapi/campg-std"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=8PWQVO6g53D8pQmtwFMV0vj37xTgUUI29X8D7fKYDwvraRDFOGE5HuR47esxPFRniJ4n30N60zrCLgFtF%2FwLmQ%3D%3D"); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("s_page", "UTF-8") + "="
				+ URLEncoder.encode(Integer.toString(idx), "UTF-8")); /* 조회 시작 지점 ( 0 부터 시작 ) */
		urlBuilder.append("&" + URLEncoder.encode("s_list", "UTF-8") + "="
				+ URLEncoder.encode("1000", "UTF-8")); /* 한 번에 조회될 최대 data 갯수 */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		return sb.toString();
	}
	
	
	public static Map<String, List<Object>> generateAPI() throws Exception {
		Map<String, List<Object>> apiMap = new HashMap<>();
		ApiParser parser = ApiParser.getInstance();
		int idx = 0;
		while(true) {
			String apiQuery = runAPI(idx);
			apiQuery = parser.parsePropertyKorToEng(apiQuery);
			String[] jsonArr = ApiParser.campingApiParser(apiQuery);
			apiMap = mergeMap(apiMap, ApiParser.getJsonList(jsonArr));
			idx+=IDX_JUMP;
			if(jsonArr.length < 1000) {
				break;
			}
		}
		return apiMap;
	}
	
	public static Map<String, List<Object>> mergeMap(
			Map<String, List<Object>> map, Map<String, List<Object>> newMap) throws Exception {
		for (String key : newMap.keySet()) {
			List<Object> tempList = new ArrayList<>();
			System.out.println("key="+key);
			if(map.containsKey(key)) {
				tempList = map.get(key);
			}
			tempList.addAll(newMap.get(key));
			map.put(key, tempList);
		}
		return map;
	}
	
	
}
