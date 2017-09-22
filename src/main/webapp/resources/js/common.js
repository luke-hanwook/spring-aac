$(document).ready(function() {
	//스크랩하기
	$(document).on('click', '.scrapbtn', function() {
		var cno = $(this).parents('.title-content').attr('id');
		var email = $('#useremail').html();

		$.ajax({
			type : 'post',
			url : '/scrap/',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				cno : cno,
				email : email,
			}),
			success : function(result) {
				if (result == 'success') {
					alert('등록되었습니다.');
					maxPage = 1;
					$('.popover-content').html(getScrapPage);
				} else if (result == 'duplicated') {
					alert('중복된 스크랩입니다.')
				}
			}, error : function(result) {
				console.log(result.status);
				if (result.status == '401') {
					alert('로그인한 이용자만 가능합니다.')
					location.href = '/user/login';
				}
			}
		});
	});
	
	//스크랩 삭제
	$(document).on('click', '.scrapdelete', function() {
		event.preventDefault();

		var sno = $(this).parents('.scrap-box').attr('data-sno');

		$.ajax({
			type : 'delete',
			url : '/scrap/' + sno,
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'DELETE'
			},
			dataType : 'text',
			success : function(result) {
				if (result == 'success') {
					alert('삭제되었습니다.');
					maxPage = 1;
					$('.popover-content').html(getScrapPage);
				}
			}
		});
	});
	
	// 스크랩 보기
	$('#scrap-star').popover({
		container : 'body',
		content : getScrapPage,
		html : true
	});
	
});

function getScrapPage() {
	var str = 'g';
	var scnt = 0;
	$.ajax({
		url : '/scrap/1',
		async : false,
		success : function(data) {
			str = data.list;
			scnt = data.scnt;
		}
	});

	var template = Handlebars.compile($('#scraptemplate').html());
	var html = template(str);
	$('.scrap-box').remove();
	$('#popover_content_wrapper').append(html);
	
	return '<div class="scrapDiv"><div style="margin-bottom:15px; text-align:right;"><small>스크랩수 <strong><span id="scnt">'
		+scnt+'</span></strong>개</small></div>'+html+'</div>';
}

function getAppendScrapPage(page) {
	var str = 'g';
	var scnt = 0;
	$.ajax({
		url : '/scrap/' + page,
		async : false,
		success : function(data) {
			str = data.list;
			scnt = data.scnt;
		}
	});

	var template = Handlebars.compile($('#scraptemplate').html());
	var html = template(str);
	
	$('.scrapDiv').append(html);
}
var maxPage = 1;

//스크롤 보이기 & 없애기
$(document).on('mouseenter', '.popover-content', function() {
	$(this).css('overflow-y', 'auto');
	// popover 스크롤 페이지
	$(this).scroll(function() {
		var scrolltop = $(this).scrollTop();
		var sCnt = $('#scnt').html();
		 if($('.scrapDiv').height() <= Math.round(scrolltop) + $('.popover-content').height()){
		   	if(maxPage < Math.ceil(sCnt / 5)) {
		    	getAppendScrapPage(++maxPage);
		    }
		 }
	});
}).on('mouseleave', '.popover-content', function() {
	$(this).css('overflow-y', 'hidden');
});

Handlebars.registerHelper('prettifyDate', function(timeValue) {
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	return year + '/' + month + '/' + date;
});