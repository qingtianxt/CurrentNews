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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#myForm").validate({
			rules : {
				title : {
					required : true
				},
				publisher : {
					required : true
				}

			},
			messages : {
				title : {
					required : "请输入新闻标题"
				},
				publisher : {
					required : "请输入新闻发布人"
				}

			}
		});
	});
</script>
<script type="text/javascript">
	function showType(obj) {
		$(obj).parent().nextAll().remove();
		if (obj == null) {
			id = 0;
		} else {
			id = obj.value;
		}
		$
				.get(
						"newsType",
						{
							method : "getType",
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-2'><select name='newsType_id' class='form-control' onchange='showType(this)' id='type0'><option value='-1'>-- 请选择父类 --</option>";
								for ( var type in data) {
									content += "<option value='"+data[type].id+"'>"
											+ data[type].name + "</option>";
								}
								content += "</select></div>";
								$("#types").append(content);
							}
						}, "json");
	};
</script>
<script type="text/javascript">
	$().ready(showType(null));
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
						value="${newsPic_bean.title}" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">发布人 </label>
				<div class="col-md-10">
					<input class="form-control" name="publisher" type="text"
						id="publisher" value="${newsPic_bean.publisher}" />
				</div>
			</div>
			<div>
				<label class="col-md-2 control-label" for="name">发布日期 </label><div class="col-md-10"> <input
					class="form-control" placeholder="请输入日期" class="laydate-icon"
					onclick="laydate()" name="publish_date"> </div>
				<div class="box"></div>
			</div>
			
			<div class="form-group col-md-12" id="types">
				<label class="col-sm-2 control-label" for="name">分类： </label>
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
					<textarea name="info" id="myEditor">${newsPic_bean.info}</textarea>
				</div>
			</div>
			<input type="hidden" name="id" value="${newsPic_bean.id}">
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