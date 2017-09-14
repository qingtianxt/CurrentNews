<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080//CurrentNews/util" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看管理员</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h1>用户列表</h1>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-1 "></div>
			<div class="col-md-10 ">
				<table class="table table-striped">
					<tr>
						<td>id</td>
						<td>用户名</td>
						<td>昵称</td>
						<td>性别</td>
						<td>创建日期</td>
						<td>邮箱</td>

						<td>用户状态</td>

						<td>操作</td>
					</tr>
					<!-- forEach遍历出adminBeans -->
					<c:forEach items="${user_Beans }" var="item" varStatus="status">
						<tr>
							<td>${item.id }</td>
							<td><a
								href="${pageContext.request.contextPath}/user?method=getByUserName&username=${item.username }">${item.username }</a></td>
							<td>${item.nickname }</td>
							<td><c:if test="${item.sex==0 }">男</c:if> <c:if
									test="${item.sex==1 }">女</c:if></td>
							<td>${item.create_date }</td>
							<td>${item.email }</td>
							<td><c:if test="${item.status==1 }">活跃</c:if> <c:if
									test="${item.status==0 }">冻结</c:if></td>
							<td><c:if test="${item.status==1 }">
									<a
										href="${pageContext.request.contextPath}/user?method=update&status=0&id=${item.id }&flag=0">冻结</a>
								</c:if> <c:if test="${item.status==0 }">
									<a
										href="${pageContext.request.contextPath}/user?method=update&status=1&id=${item.id }&flag=0">解冻</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div align="center">
		<util:page pagingBean="${user_pagingBean }" />
	</div>
	<c:if test="${not empty msg }">
		<div align="center" class="alert alert-info" role="alert">${msg }</div>
	</c:if>

</body>
</html>