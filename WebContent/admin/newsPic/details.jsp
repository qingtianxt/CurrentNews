<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻详情</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<h1>新闻详情</h1>
		<div class="col-md-12">
			<img class="img-rounded" alt="新闻图片" src="${pageContext.request.contextPath }/${newsPic_bean.pic}" />
		</div>
		<table class="table table-striped">
			<tr>
				<td>新闻标题</td>
				<td>${newsPic_bean.title}</td>
			</tr>
			<tr>
				<td>新闻发布人</td>
				<td>${newsPic_bean.publisher}</td>
			</tr>
			<tr>
				<td>创建时间</td>
				<td>${newsPic_bean.create_date}</td>
			</tr>
		</table>
		<article>
		<h3>新闻内容</h3>
		${newsPic_bean.info} </article>
	</div>
</body>
</html>