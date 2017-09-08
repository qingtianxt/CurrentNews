<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类型查询</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/js/jquery-1.12.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/
bootstrap-3.3.5-dist/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h1>商品类型查询</h1>
		<table class="table table-striped">
			<ol class="breadcrumb">
				<li><a href="newsType?method=list&id=0">Home</a></li>
				<li><a
					href="newsType?method=list&id=${newsTypeBean.parentId}">上一级</a></li>
				<li class="active">${newsTypeBean.name}</li>
			</ol>
			<c:forEach items="${newsTypeBean.childBeans}" var="item"
				varStatus="status">
				<c:if test="${status.index==0}">
					<tr>
						<td>id</td>
						<td>分类名</td>
						<td>描述</td>
						<td>创建时间</td>
						<td>操作</td>
						<td>操作</td>
					</tr>
				</c:if>
				<tr>
					<td>${item.id}</td>
					<td><a href="newsType?method=list&id=${item.id}">${item.name}</a></td>
					<td>${item.intro}</td>
					<td>${item.create_date}</td>
					<td><a href="newsType?method=updateUI&id=${item.id}">修改
					</a></td>
					<td>
					<%-- <a href="newsType?method=delete&id=${item.id}">删除</a> --%>
					<a href="javascript:void(0)" onclick="delete1('${item.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty msg}">
					<div class="alert alert-danger" role="alert">${msg }</div>
				</c:if>
	</div>
	<script type="text/javascript">
		
		function delete1(id){
			if(confirm("你确定要删除他和他的子分类吗")){
				location.href="${pageContext.request.contextPath}/newsType?method=delete&id="+id;
			}
		}
	</script>
</body>
</html>