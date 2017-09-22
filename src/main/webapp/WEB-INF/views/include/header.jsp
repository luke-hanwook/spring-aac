<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.10/handlebars.js"></script>

<script src="/resources/js/common.js"></script>
 
<style type="text/css">
	header {
		background-color: #337ab7;
	}
	
	footer {
		height: 100px;
	}
	
	nav {
		height: 50px;
		margin: 0 auto;
		padding-left: 105px;
	}
	
	.filter select {
		width: 110px;
		margin-top: 20px;
	}
	
	.sortyselectfilter {
		height: 10px;
	}
	
	#totCnt {
		font-size: 12px;
		position: relative;
		margin-left: 10px;
	}
	
	#sortyselect {
		width: 100px;
		float: right;
	}
	
	.filter select, nav ul li, nav ul, #sortyselect{
		display: inline-block;
	}
	
	.filter {
		padding-left: 10px;
		padding-right: 10px;
	}
	
	.filter, .panel-group, section {
		width: 1000px;
		margin: 0 auto;
	}
	
	.panel {
		width: 600px;
		margin: 10px 10px;
	}
	
	.panel-sidebar {
		width : 370px;
		border: 1px solid black;
		float: right;
		margin: 30px 10px;
		height: 400px;
	}
	
	body {
		background-color: #F6F6F6;
	}
	
	nav ul li{
		margin-top: 15px;
		margin-right: 25px;
	}
	
	nav a:link, nav a:visited {
		font-size: 15px;
		color: white;
		text-decoration: none;
	}
	
	article a:link, article a:visited, .scrap-box a:link, .scrap-box a:visited {
		color: black;
		text-decoration: none;
		margin: 0;
	}
	
	.image-circle, .title, .scrap, .title-content {
		margin-top : 5px;
		display: inline-block;
	}
	
	.image-circle {
		position: relative;
		top: -20px;
		left: 0;
		margin-right: 10px;
	}
	
	.panel-heading {
		padding-bottom: 0px;
	}
	
	.detail {
		position: relative;
		height: 300px;
	}
	
	.detail img {
    	width: 100%;
	}
	
	.detail .title-content {
		position: absolute;
	    bottom: 20px;
	    left: 16px;
	    color: white;
	}
	
	.box-body table {
		height: 400px;
		width: 600px;
		table-layout: fixed;
	}
	
	.box-body table th, .box-body table td{
		padding-left: 10px;
		display: table-cell;
	}
	
	.copyright {
		text-align: right;
		padding-top: 30px;
	}
	
	.nav-tabs li {
		text-align: center;
		width: 120px;
	}
	
	.rightbox {
		float: right;
		display: inline-block;
		height: 50px;
		margin: 0 auto;
		padding-right: 130px;
		padding-top: 15px;
	}
	
	.rightbox a {
		margin-left: 25px;
	}
	
	.help-block {
		color: red;
		font-size: 10px;
	}
	
	#userprofile {
		display: inline-block;
		color: white;
	}
	
	.star-group span {
		display: none;
	}
	
	.timeline {
		list-style: none;
		margin:0px; 
		padding:0px;
	}
	
	.timeline-item {
		width: 1000px;
		margin-left: 15px;
	}
	
	.star-group {
		margin-top: 20px;
	}
	
	#starrating {
		font-size: 30px;
	}
	
	#replyBtn {
		float: right;
	}
	
	.timeline-info {
		height: 180px;
		display: table-cell;
		font-size: 20px;
		line-height: 120%;
		vertical-align: middle;
		text-align: center;
		width: 343px;
	}
	
	.timeline-item {
		display: table-cell;
		vertical-align: middle;
	}
	
	.timeline-info span {
		font-style: italic;
		color: #337ab7;
	}
	
	.timeline-option {
		margin-top: 20px;
		float: right;
		z-index: 1;
	}
	
	.timeline-item .form-control {
		 -webkit-appearance: none;
		  -webkit-box-shadow: none;
		  -moz-box-shadow: none;
		  box-shadow: none;
	}
	
	.popover-content {
    	width:260px;
    	height:250px;
     	overflow-y: hidden;
	}
</style>
 
</head>
<body>
<header>
	<nav>
		<ul>
			<li><a href="/">홈</a></li>
			<li><a href="/camping/list">리뷰</a></li>
			<li><a href="#">추천</a></li>
		</ul>
		
		<div class="rightbox">
		<sec:authorize access="isAnonymous()">
			<a href="/user/login">로그인</a>
			<a href="/user/join">회원가입</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication var="user" property="details"/>
			<span id="userprofile">${user.nick}</span>
			<span id="useremail" style="display: none;">${user.username}</span>
			<a href="#" title="스크랩" data-toggle="popover" 
				data-placement="bottom" data-trigger="click" id="scrap-star">
				<i class="fa fa-star"></i></a>
			<a href="/user/logout">로그아웃</a>
		</sec:authorize>
		</div>
	</nav>
</header>

<div id="popover_content_wrapper" style="display: none"></div>

<script type="text/javascript">
	
	$(document).ready(function() {
		var token = '${_csrf.token}';
		var header = '${_csrf.headerName}';
		
		$(function() {
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
		
	});

</script>

<script type="text/x-handlebars-template" id="scraptemplate">
	{{#each .}}
		<div class="scrap-box" data-sno={{sno}}><a href="/camping/list/{{name}}">
		<strong>{{name}}</strong></a> <small>{{cityname}}/{{classifyname}} 
		{{prettifyDate regdate}}</small>
		<a href="#"><i class="material-icons scrapdelete" style="font-size:16px">close</i></a></div>
		<hr>
	{{/each}}
</script>