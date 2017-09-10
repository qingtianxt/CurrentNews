<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻添加</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/static/ueditor/themes/default/css/ueditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/
static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>
<script type="text/javascript">
$().ready(function() {
$("#myForm").validate({
	rules:{
		title: {
			required : true
		},
		publisher: {
			required : true
		}
		
	},
	messages:{
		title: {
			required:"请输入新闻标题"
		},
		publisher:{
			required:"请输入新闻发布人"
		}		
	
	}
});
});
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center text-danger">添加新闻</h1>
		<!-- 需要把表单提交提交方式改为文件流的方式 -->
		<form enctype="multipart/form-data" id="myForm" role="form"
			action="newsPic?method=add" method="post">
			<!-- 路径问题 当路径 -->
			
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">新闻标题 </label>
				<div class="col-md-10">
					<input class="form-control" name="title" type="text" id="title"
						value="${productBean.name}" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">发布人 </label>
				<div class="col-md-10">
					<input class="form-control" name="publisher" type="text" id="publisher"
						value="${productBean.name}" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">新闻图片： </label>
				<div class="col-md-10">
					<input class="form-control" name="pic" type="file" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">新闻内容： </label>
				<div class="col-md-10" style="width: 800px; margin: 20px auto 40px;">
					<textarea name="info" id="myEditor">${productBean.intro}</textarea>
				</div>
			</div>
			<input type="hidden" name="id" value="${productBean.id}">
			<div class="form-group col-md-12">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
				</div>
			</div>
		</form>
		<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">添加成功</div>
		</c:if>
		<script type="text/javascript">
			var editor = new UE.ui.Editor();
			editor.render("myEditor");
		</script>
	</div>
</body>
</html>