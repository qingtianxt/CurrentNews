<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://localhost:8080//CurrentNews/util" prefix="util"  %>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
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
				<h1>管理员列表</h1>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-1 "></div>
			<div class="col-md-10 ">
				<table class="table table-striped">
					<tr>
						<td>id</td>
						<td>username</td>
						<td>password</td>
						<td>salt</td>
						<td align="center" colspan="2">操作</td>
					</tr>
					<!-- forEach遍历出adminBeans -->
					<c:forEach items="${adminUser_list}" var="item" varStatus="status">
						<tr>
							<td>${item.id }</td>
							<td><a>${item.username }</a></td>
							<td>${item.password }</td>
							<td>${item.salt }</td>
							<td><a
								href="${pageContext.request.contextPath}/adminUser?method=updateUI&id=${item.id}">修改
							</a></td>
							<td>
							<%-- <a href="${pageContext.request.contextPath}/adminUser?method=delete&id=${item.id}">删除</a>
							 --%>
							 <a href="javascript:void(0);" class="delete" onclick="delete1('${item.id}')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${not empty msg}">
					<div class="alert alert-success" role="alert">${msg }</div>
				</c:if>
				
			</div>
			<div class=" col-md-1"></div>
		</div>
		<div class="row-fluid">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div>
					<util:page pagingBean="${adminUser_pagingBean}" />
					<div style="color: red"></div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script type="text/javascript">
		function delete1(id) {
			if(confirm("您确认要删除吗?"))
				{
					location.href="${pageContext.request.contextPath}/adminUser?method=delete&id="+id;
				}
		}
	</script>
</body>
</html>