<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=jyhufK2AO0m54ULWtj2x&submodules=geocoder"></script>
	
<section>
	<div class="box-header">
		<div class="detail">
			<img alt="" src="" width="100%" height="100%">
			<div class="title-content" id="${vo._id}">
				<div class="title">
					<h2>
						<span>${vo.name}</span>
					</h2>
				</div>
				<div class="scrap">
					<button type="button" class="btn btn-info btn-xs scrapbtn">
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
		<li class="info active"><a href="#box-body">Info<br>캠핑장정보</a></li>
		<li class="review"><a href="#menu1" id="navreview"><span id="totrCnt"></span><br>리뷰</a></li>
		<li class="image"><a href="#menu2" id="navimage"><span id="totiCnt"></span><br>이미지</a></li>
	</ul>

	<div class="image box-body" style="background-color: white; display: none;">
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="image-info">
					<a class="btn btn-primary btn" id="imageBtn">새로올리기</a>
					<input type="file" id="file" name="profile_image" style="display:none;">
				</div>
			</div>
		</div>
		
		<div class="row imageArea">
			<div class="col-sm-12">
				<ul class="uploadedList">
					<li id="imageDiv"></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="review box-body" style="background-color: white; display: none;">
		<div class="row">
			<div class="col-sm-4 text-center">
				<div class="timeline-info">
					전체 <span id="replyCnt"></span>개의 리뷰<br>
					평점 : <span id="starAvg"></span>
				</div>
			</div>
			<div class="col-sm-8 text-center">
				<div class="star-group">
					<div id="starrating">5</div>
						<i class="fa fa-star" style="font-size: 24px; color: orange;"><span>1</span></i>
						<i class="fa fa-star" style="font-size: 24px; color: orange;"><span>2</span></i>
						<i class="fa fa-star" style="font-size: 24px; color: orange;"><span>3</span></i>
						<i class="fa fa-star" style="font-size: 24px; color: orange;"><span>4</span></i>
						<i class="fa fa-star" style="font-size: 24px; color: orange;"><span>5</span></i>
				</div>
			<div class="form-group">
				<input class="form-control" type="text" name="content" id="replycontent" placeholder="리뷰를 적어주세요.">
			</div>
			<a class="btn btn-primary btn" id="replyBtn">입력</a>
			
			</div>
			
			<ul class="timeline">
				<li class="time-label" id="reviewDiv"></li>
			</ul>
		</div>
	</div>
	
	<div class="info box-body" style="background-color: white;">
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
<script type="text/javascript">
//이미지
	var cno = ${vo._id };
	var maxImagePage = 1;
	
	$('#imageBtn').click(function() {
		if($('#useremail').html() == null) {
			alert('로그인 후 가능합니다.');
			self.location = '/user/login';
			return;
		}
		MyFunction();
		return false;
	});
	
	$('#navimage').on('click', function() {
		getImagePage('/uploadAjax/' + cno + '/1');
	});
	
	$.getJSON('/uploadAjax/' + cno + '/1', function(data) {
		totiCnt = data.acnt;
		$('#totiCnt').html(totiCnt);
	});
	
	function getImagePage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			$('#totiCnt').html(data.acnt);
			printImageData(data.list, $('#imageDiv'), $('#templateImage'));
		});
	}
	
	var printImageData = function(ImageArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(getFileInfo(ImageArr));
		$('.imageLi').remove();
		target.after(html);
	};
	
	function MyFunction() {
		$('#file').click();
	}
	
	function getAppendImagePage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			appendImageData(data.list, $('.uploadedList'), $('#templateImage'));
		});
	}
	
	var appendImageData = function(ImageArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(getFileInfo(ImageArr));
		target.append(html);
	};
	
	//이미지 insert
	$('#file').on('change', function (event) {
		
		var file = $(this)[0].files[0];
	
		var formData = new FormData();
		formData.append('file', file);
		
		$.ajax({
			url : '/uploadAjax/'+cno,
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(result) {
				if(result == 'success') {
					alert('등록 되었습니다.');
					maxImagePage = 1;
					getImagePage('/uploadAjax/' + cno + '/1');
				}
			}
		});
		
	});
	
	$('.imageArea').on('dragenter dragover', function() {
		event.preventDefault();
	});
	
	$('.imageArea').on('drop', function(event) {
		event.preventDefault();
		
		if($('#useremail').html() == null) {
			alert('로그인 후 가능합니다.');
			self.location = '/user/login';
			return;
		}
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];
		
		var formData = new FormData();
		formData.append('file', file);
		
		$.ajax({
			url : '/uploadAjax/'+cno,
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(result) {
				if(result == 'success') {
					alert('등록 되었습니다.');
					maxImagePage = 1;
					getImagePage('/uploadAjax/' + cno + '/1');
				}
			}
		});
	});
	
	$(document).on('mouseenter', '.imgDiv', function() {
		$(this).find('.imgeff').css('visibility', 'visible');
	}).on('mouseleave', '.imgDiv', function() {
		$(this).find('.imgeff').css('visibility', 'hidden');
	});
	
	$(document).on('click', '.imgDel .btn', function(event) {
		event.preventDefault();
		
		var that = $(this).parents('li');
		var ano = that.attr('data-ano');

		$.ajax({
			url : '/deleteFile/' + ano,
			type : 'post',
			data : {
				fileName : that.attr('data-src')
			},
			dataType : 'text',
			success : function(result) {
				if(result == 'success') {
					alert('삭제 되었습니다.');
					maxImagePage = 1;
					getImagePage('/uploadAjax/' + cno + '/1');
				}
			}
			
		});
	});
	
	$(document).on('click', '.oribtn .btn', function(event) {
		event.preventDefault();
		
		var maskHeight = $(document).height();  
		var maskWidth = $(window).width();  

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('.popback').css({'width':maskWidth,'height':maskHeight}); 
		
		$('body').on('scroll mousewheel', function(e) {
		    e.preventDefault();
		    e.stopPropagation();
		    return false;
		});
		
		var that = $(this).parents('li');
		var fileLink = $(this).attr("href");
		
		var fileName = that.attr('data-name');
		var email = that.attr('data-email');
		var regdate = that.attr('data-regdate');
		var ano = that.attr('data-ano');
		
		var nameTag = $('#popup_name');
		nameTag.text(fileName);
		var imgTag = $('#popup_img');
		imgTag.attr('src', fileLink);
		
		var emailTag = $('#popup_email');
		emailTag.text(email);
		
		var dateTag = $('#popup_regdate');
		dateTag.text(regdate);
		
		$('.popup').show();
		imgTag.addClass('show');
		
	});
	
	$('#popup_close').on('click', function() {
		event.preventDefault();
		$('body').off('scroll mousewheel');
		
		var imgTag = $('#popup_img');
		imgTag.attr('src', '');
		$('.popup').hide();
	});
	
	function checkImageType(fileName) {
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);
	}
	
	function getFileInfo(ImageArr) {
		
		var retObj = [];
		
		$(ImageArr).each(function(i, item) {
			var regdate = item.regdate;
			var email = item.email;
			var ano = item.ano;
			var fullName = item.fullpath;
			var fileName, imgsrc, getLink;
			var oriFilePath;
			
			if(checkImageType(fullName)) {
				imgsrc = '/displayFile?fileName='+fullName;
				
				var front = fullName.substr(0,14);
				var end = fullName.substr(16);
				oriFilePath = front+end;
				
				getLink = '/displayFile?fileName='+oriFilePath;
			}
			
			fileName = oriFilePath.substr(oriFilePath.indexOf('_')+1);
			
			retObj[i] = {fileName:fileName, imgsrc:imgsrc, getLink:getLink, 
					fullName:fullName, email:email, ano : ano, regdate : regdate};
		});
		
		return retObj;
	}
	
</script>

<script type="text/x-handlebars-template" id="templateImage">
{{#each .}}
	<li class="imageLi" data-src="{{fullName}}" data-ano="{{ano}}" 
			data-name="{{fileName}}" data-email="{{email}}" data-regdate="{{regdate}}">
		<div class="col-sm-4 text-center imgDiv">
			<div class="imgeff back">
			{{#eqEmail email}}
				<div class="imgeff imgDel">
					<a type="button" class="btn btn-danger btn-sm">
						<span class="glyphicon glyphicon-remove"></span> 삭제 하기
					</a>
				</div>
			{{/eqEmail}}
				<div class="imgeff oribtn">
					<a type="button" class="btn btn-default btn-sm" href="{{getLink}}">
          				<span class="glyphicon glyphicon-eye-open"></span> 원본 보기
        			</a>
				</div>
			</div>
			<div class="upimgDiv">
				<img class="upImage" src="{{imgsrc}}" alt="Attachment">
			</div>
		</div>
	</li>
{{/each}}
</script>

<script type="text/javascript">
//리뷰
	var maxPage = 1;
	var curTab = '';
	var totrCnt = 0;
	
	var navli = $('.nav-tabs').children();
	var cno = ${vo._id };
	navli.on('click', function(event) {
		event.preventDefault();
		navli.removeClass('active');
		$('.box-body').css('display', 'none');
		curTab = $(this).attr('class');
		maxPage = 1;
		maxImagePage = 1;
		$('.'+$(this).attr('class')).css('display', '');
		$(this).addClass('active');
	});

	//리스팅
	$('#navreview').on('click', function() {
		getPage('/review/' + cno + '/1');
	});
	
	$.getJSON('/review/' + cno + '/1', function(data) {
		totrCnt = data.cnt;
		$('#totrCnt').html(totrCnt);
	});

	$(window).scroll(function(){
		 var scrolltop = $(window).scrollTop();
		 totrCnt = $('#totrCnt').html();
		 totiCnt = $('#totiCnt').html();
		 // 스크롤 리스팅
		 if(curTab == 'review' && Math.round(scrolltop) == $(document).height() - $(window).height()){
		   	if(maxPage < Math.ceil(totrCnt / 5)) {
		    	getAppendPage('/review/' + cno + '/' + (++maxPage));
		    }
		 } else if (curTab == 'image' && $(document).height() <= Math.round(scrolltop) + $(window).height()){
			 if(maxImagePage < Math.ceil(totiCnt / 9)) {
				 getAppendImagePage('/uploadAjax/' + cno + '/' + (++maxImagePage));
			 }
		 }
	});

	//별점주기
	var star_group = $('.star-group').find('.fa-star');
	$(star_group).on('mouseenter', function() {

		var curStar = $(this).find('span').html();

		for (var i = 0; i < Number(curStar) - 1; i++) {
			$(star_group).eq(i).attr('class', 'fa fa-star');
		}

		$(star_group).eq(Number(curStar) - 1).attr('class', 'fa fa-star-half-o');

		for (var i = Number(curStar); i < star_group.length; i++) {
			$(star_group).eq(i).attr('class', 'fa fa-star-o');
		}

		$('#starrating').html(curStar - 1);

		}).on('mouseleave', function(event) {
		var curStar = $(this).find('span').html();
		var obj = $(this).offset();

		if (obj.left < event.pageX) {
			console.log('right');
			for (var i = 0; i < Number(curStar); i++) {
				$(star_group).eq(i).attr('class', 'fa fa-star');
			}

			$('#starrating').html(curStar);
		} else if (obj.left > event.pageX) {
			console.log('left');

			for (var i = Number(curStar) - 1; i < star_group.length; i++) {
				$(star_group).eq(i).attr('class', 'fa fa-star-o');
			}

			$('#starrating').html(curStar - 1);
		}

	});

	$(star_group).on('click', function() {
		var curStar = $('#starrating').html();

		$('#starrating').html(curStar);

		for (var i = 0; i < Number(curStar); i++) {
			$(star_group).eq(i).attr('class', 'fa fa-star');
		}

	});

	//글입력
	$('#replyBtn').on('click', function() {
		var content = $('#replycontent').val();
		var star = $('#starrating').text();
		var email = $('#useremail').html();
		
		if (content.length <= 0) {
			alert('리뷰를 작성하세요.');
			return;
		}

		$.ajax({
			type : 'post',
			url : '/review',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				cno : cno,
				email : email,
				content : content,
				star : star
			}),
			success : function(result) {
				if (result == 'success') {
					alert('등록되었습니다.');
					$('#totrCnt').html(++totrCnt);
					maxPage = 1;
					getPage('/review/' + cno + '/1');
					$('#replycontent').val('');
				}
			}, error : function(result) {
				if(result.status == '401') {
					alert('로그인한 이용자만 가능합니다.')
					location.href = '/user/login';
				}
			}
		});
	});

	//페이지 뿌리기
	function getPage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			$('#starAvg').html(data.avg.toFixed(1));
			$('#replyCnt').html(data.cnt);
			printData(data.list, $('#reviewDiv'), $('#template'));
		});
	}
	
	function getAppendPage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			appendData(data.list, $('.timeline'), $('#template'));
		});
	}
	
	var appendData = function(replyArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		target.append(html);
	};
	
	var printData = function(replyArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		$('.replyLi').remove();
		target.after(html);
	};

	//페이지 hover
	$(document).on('mouseenter', '.timeline-item', function() {
		$(this).css("background-color", "#EAEAEA");
		$(this).find('.fa-cog').css('display', '');
		$(this).find('.fa-trash-o').css('display', '');
	}).on('mouseleave', '.timeline-item', function() {
		$(this).css("background-color", "white");
		$(this).find('input').css("border", "none");
		$(this).find('input').attr("readonly", "readonly");
		$(this).find('.fa-trash-o').css('display', 'none');
		$(this).find('.fa-cog').css('display', 'none');
		$(this).find('.btn').css('display', 'none');
	});

	//글삭제
	$(document).on('click', '.fa-trash-o', function(event) {
		event.preventDefault();

		var rno = $(this).parents('.replyLi').attr('data-rno');

		$.ajax({
			type : 'delete',
			url : '/review/' + rno,
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'DELETE'
			},
			dataType : 'text',
			success : function(result) {
				if (result == 'success') {
					alert('삭제되었습니다.');
					$('#totrCnt').html(--totrCnt);
					getPage('/review/' + cno + '/1');
					maxPage = 1;
				}
			}
		});
	});

	//글수정 선택
	$(document).on('click', '.fa-cog', function() {
		event.preventDefault();
		var star_group = $('.star-group').clone();

		var rno = $(this).parents('.replyLi').attr('data-rno');
		var inputcontent = $(this).parents('.replyLi').find('input');
		$(inputcontent).removeAttr('readonly');
		inputcontent.css('border', '');
		var btn = $(this).parents('.replyLi').find('.btn');
		btn.css('display', '');

		console.log($(this).parents('.replyLi').find('h3').text());
		console.log($(star_group).find('#starrating').html($(this).parents('.replyLi').find('h3').text()));

	});

	//글수정저장
	$(document).on('click', '#saveBtn', function() {
		var rno = $(this).parents('.replyLi').attr('data-rno');
		var content = $(this).parents('.replyLi').find('#inputcontent').val();
		var star = $(this).parents('.replyLi').find('#starrating').text();

		$.ajax({
			type : 'put',
			url : '/review/' + rno,
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'PUT'
			},
			dataType : 'text',
			data : JSON.stringify({
				star : star,
				content : content
			}),
			success : function(result) {
				if (result == 'success') {
					alert('수정되었습니다.');
					getPage('/review/' + cno + '/1');
					maxPage = 1;
				}
			}
		});
	});

	Handlebars.registerHelper('prettifyDate', function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + '/' + month + '/' + date;
	});

	Handlebars.registerHelper('eqEmail', function(email, block) {
		var accum = '';
		if (email == '${user.username}') {
			accum += block.fn();
		}
		return accum;
	});

	Handlebars.registerHelper('forStar', function(star) {
		var str = '';
		var cnt = 0;
		for (var i = 0; i < star; i++) {
			str += '<i class="fa fa-star" style="color: orange;"><span>'
					+ (++cnt) + '</span></i>';
		}
		
		for(var i = 0; i < 5-star; i++) {
			str += '<i class="fa fa-star-o" style="color: orange;"><span>'
				+ (++cnt) + '</span></i>';
		}

		return new Handlebars.SafeString(str);
	});
</script>

<script type="text/x-handlebars-template" id="template">
	{{#each .}}
	<li class="replyLi" data-rno={{rno}}>
	<div class="timeline-item col-sm-12">
		<div class="col-sm-2 text-center">
			<div class="star-group">
				<div id="starrating"><h3>{{star}}</h3></div>
				{{forStar star}}
			</div>
        </div>
        <div class="col-sm-10">
          <h4>{{nick}} <small>{{prettifyDate regdate}}</small>
			{{#eqEmail email}}
			<a class="btn btn-primary btn-xs" style="display:none; float: right;" id="saveBtn">save</a>
			<a href=""><i class="fa fa-cog" style="display:none;"></i></a>
			<a href=""><i class="fa fa-trash-o" style="display:none;"></i></a>
			{{/eqEmail }}</h4>
          <div class="form-group">
			<input class="form-control" type="text" value="{{content}}" readonly="readonly" style="border:none" id="inputcontent">
          </div>
			
        </div>
	</div>
	</li>
	{{/each}}
</script>
<%@ include file="../include/footer.jsp"%>