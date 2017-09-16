<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!--导入css-->
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!-- validate验证 -->
<script src="${pageContext.request.contextPath}/static/js/myValidate.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
	type="text/javascript"></script>
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
			<img class="img-rounded" alt="新闻图片"
				src="${pageContext.request.contextPath }/${newsPic_bean.pic}" />
		</div>
		<table class="table table-striped">
			<tr>
				<td>手机号</td>
				<td>${session_user.username }</td>
			</tr>
			<tr>
				<td>性别</td>
				<td><c:if test="${session_user.sex==0 }">男</c:if> <c:if
						test="${session_user.sex==1 }">女</c:if></td>
			</tr>
			<tr>
				<td>昵称</td>
				<td>${session_user.nickname }</td>
			</tr>
			<tr>
				<td>当前密码</td>
				<td>${session_user.password }</td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td>${session_user.email }</td>
			</tr>
		</table>
		<button class="btn btn-default btn-sm" data-toggle="modal"
			data-target="#myModal">修改信息</button>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改信息</h4>
					</div>
					<div class="modal-body">

						<form role="form" id="checkForm" target="_top"
							action="${pageContext.request.contextPath}/user?method=updateInfo&id=${session_user.id}&salt=${session_user.salt}"
							method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">新手机号</label> <input type="text"
									class="form-control" name="username" id="username"
									placeholder="新手机号" value="${session_user.username }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">昵称</label> <input type="text"
									class="form-control" name="nickname" id="nickname"
									placeholder="昵称" value="${session_user.nickname }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">原密码</label> <input
									type="password" class="form-control" name="pas" id="pas"
									placeholder="原密码" value="${session_user.password }"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">新密码</label> <input
									type="password" class="form-control" name="Npassword"
									id="password" placeholder="新密码"
									value="${session_user.password }">
							</div>
					</div>
					<div class="modal-footer">
						<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">提交更改</button>
					</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</div>




</body>
</html>