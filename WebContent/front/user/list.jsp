<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080//CurrentNews/util" prefix="util"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻展示</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<h1>新闻查询</h1>
		<table class="table table-striped">
			<c:forEach items="${collect_bean}" var="item" varStatus="status">
				<c:if test="${status.index==0 }">
					<!-- <ol class="breadcrumb" id="index0">
					</ol>
					<script>showIndex();</script> -->
					<tr>
						<td>id</td>
						<td>新闻标题</td>
						<td>发布人</td>
						<td>发布的账户名</td>
						<td>发布日期</td>
						<td>收藏日期</td>
						<td>操作</td>
					</tr>
				</c:if>
				<tr>
					<td>${item.id }</td>
					<td><a href="${pageContext.request.contextPath }/newsPic?method=listDetails&id=${item.newsPic.id }">${item.newsPic.title}</a></td>
					<td>${item.newsPic.publisher }</td>
					<td>${item.newsPic.username }</td>
					<td>${item.newsPic.publish_date }</td>
					<td>${item.collect_date }</td>
					<td><a href="javascript:void(0)" onclick="delete1('${item.id}')">取消收藏
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<util:page pagingBean="${collect_pagingBean }"/>
	</div>
	<script type="text/javascript">
		function delete1(id){
			if(confirm("你确定要取消收藏吗")){
				location.href="${pageContext.request.contextPath }/collect?method=delete&id="+id;
			}
		}
	</script>
</body>
</html>