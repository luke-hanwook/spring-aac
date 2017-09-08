<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=jyhufK2AO0m54ULWtj2x&submodules=geocoder"></script>
<section>
	<div class="box-header">
		<div class="detail">
			<img alt="" src="http://skinnovation-if.com/wp-content/uploads/2016/08/%EC%BA%A0%ED%95%91%EC%9E%A5-%EC%86%8D-%EC%84%9D%EC%9C%A0_main.png" width="100%" height="100%">
			<div class="title-content">
				<div class="title">
					<h2>
						<span>${vo.name}</span>
					</h2>
				</div>
				<div class="scrap">
					<button type="button" class="btn btn-info btn-xs">
						<i class="fa fa-plus"></i> 스크랩
					</button>
				</div>
				<div class="title-footer">
					<span>${vo.cityname}/${vo.classifyname}</span>
				</div>
			</div>
		</div>
	</div>
	<div></div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#box-body">Info<br>캠핑장정보</a></li>
		<li><a href="#menu1">1<br>리뷰</a></li>
		<li><a href="#menu2">2<br>이미지</a></li>
	</ul>

	<div class="box-body" style="background-color: white;">
		<aside>
			<div id="map" style="width: 400px; height: 400px; float: right;"></div>
		</aside>
		<table>
			<tr>
				<th colspan="2">주소</th>
			</tr>
			<tr>
				<td colspan="2">${vo.addrNumber }</td>
			</tr>
			<tr>
				<th>가격</th>
				<th>편의시설</th>
			</tr>
			<tr>
				<td>${vo.price }</td>
				<td>${vo.facilities }<br>(${vo.safetyfacilities })</td>
			</tr>
			<tr>
				<th>운영시간</th>
				<th>전화번호</th>
			</tr>
			<tr>
				<td>${vo.operatingTime }</td>
				<td>${vo.telM } (${vo.telC })</td>
			</tr>
			<tr>
				<th>사이트/주차장수</th>
				<th>대지/건축면적</th>
			</tr>
			<tr>
				<td>${vo.siteCnt }/${vo.parkingCnt }</td>
				<td>${vo.siteArea }/${vo.architectureArea }</td>
			</tr>
			<tr>
				<th colspan="2">업데이트날짜</th>
			</tr>
			<tr>
				<td colspan="2">${vo.updatedate }</td>
			</tr>
		</table>
	</div>

	<div class="box-footer"></div>

	<script>
		var lat = ${vo.latitude};
		var lng = ${vo.longitude};
		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(lat, lng),
			zoom : 11
		});

		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(lat, lng),
			map : map
		});
	</script>

</section>

<%@ include file="../include/footer.jsp"%>