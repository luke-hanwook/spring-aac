<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section>
	<div class="box box-primary">
	
		<div class="box-header">
			<div class="filter">
				<select class="form-control" id="cityselect">
					<option value="0">지역</option>
					<c:forEach items="${cityList}" var="city">
						<option value="${city.citycode}" 
						<c:out value="${city.citycode eq pageData.cri.city ? 'selected' : ''}"/>>
						${city.cityname}</option>
					</c:forEach>
				</select>
				<select class="form-control" id="classifyselect">
					<option value="0">분류</option>
					<c:forEach items="${classifyList}" var="classify">
						<option value="${classify.classifycode}"
						<c:out value="${classify.classifycode eq pageData.cri.category ? 'selected' : ''}"/>>
						${classify.classifyname}</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		
		<div class="box-body">
			<div class="panel-group">
				<aside>
					<div class="panel-sidebar">
						<h1>sidebar</h1>
					</div>
				</aside>
				<article>
					<div class="sortyselectfilter">
						<select class="form-control" id="sortyselect">
							<option>추천순</option>
						</select>
					</div>
					<span id="totCnt">총 <strong style="color: #337ab7;">${pageData.totCount}</strong>개의 검색결과</span>
					<c:forEach items="${list}" var="vo">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color: white;">
								<div class="image-circle">
									<img src="/resources/img/default.png" class="img-circle" height="65" width="65" alt="Avatar">
								</div>
								<div class="title-content" id="${vo._id}">
									<div class="title">
										<h4><span><a href="/camping/list/${vo.name}">${vo.name}</a></span></h4>
									</div>
									<div class="scrap">
										<button type="button" class="btn btn-info btn-xs scrapbtn"><i class="fa fa-plus"></i> 스크랩</button>
									</div>
									<div class="title-footer">
										<span>${vo.cityname}/${vo.classifyname}</span>
									</div>
								</div>
							</div>
<!-- 							<div class="panel-body">reviewreviewreviewreviewreviewreview</div> -->
						</div>
					</c:forEach>
				</article>
			</div>
		</div>
		
		<div class="box-footer">
			<div class="text-center">
				<ul class="pagination pagination-lg">
					<c:if test="${pageData.startPage != 1}">
						<li <c:out value="${pageData.cri.page == i? 'class=active' : '' }" />>
						<a href="1">1</a></li>
						<li class="disabled"><a href="#">...</a></li>
					</c:if>
					<c:forEach var="i" begin="${pageData.startPage }" end="${pageData.endPage }">
						<li <c:out value="${pageData.cri.page == i? 'class=active' : '' }" />>
						<a href="${i}">${i}</a></li>
					</c:forEach>
					<c:if test="${pageData.endPage != pageData.totPages}">
						<li class="disabled"><a href="#">...</a></li>
						<li <c:out value="${pageData.cri.page == i? 'class=active' : '' }" />>
						<a href="${pageData.totPages}">${pageData.totPages}</a></li>
					</c:if>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$('.pagination').on('click', 'li a', function () {
			event.preventDefault();
			var page = $(this).attr('href');
			self.location = "list"
	 			+ '?page='+page+'&perPageNum=${pageData.cri.perPageNum}' 
	 			+ getQuery();
		});
		
		$('select').on('change', function() {
			var val = $(this).val();
			
			if(val <= 0) {
				alert('옵션을 선택하세요');
				return;
			}
			
			self.location = "list"
	 			+ '${pageData.makeQuery(1)}' 
	 			+ getQuery(val);
		});
		
		function getQuery(val) {
			var query = '';
			
			if(val != undefined) {
				query += '&' + conditionFunc(val);
			}
			
			var obj = uriCheckFunc();
			
			$.each(obj, function(key, value) {
				if(value != '' && query.search(key) < 0) {
					query += '&' + key + '=' + value;
				}
			});
			
			return query;
		}
		
		function conditionFunc(val) {
			var result = '';
			if(val > 0 && val <= ${fn:length(cityList)}) {
				result = 'city=';
			} else if(val <= (${fn:length(classifyList)} * 100)) {
				result = 'category=';
			}
			return result + val;
		}
		
		function uriCheckFunc() {
			var uriObj = {
				city : '${pageData.cri.city}',
				category : '${pageData.cri.category}',
				sort : '${pageData.cri.sort}'
			};
			return uriObj;
		}
		
	</script>
</section>

<%@ include file="../include/footer.jsp"%>