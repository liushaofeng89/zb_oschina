<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.wtkj.common.GlobalConstant"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/plugins/datagrid-groupview.js" charset="utf-8"></script>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>请假申请</title>
</head>
<body>
	<div class="easyui-panel" title="请假申请" style="width:100%;max-width:600px;padding:30px 60px;">
		<form id="ff" method="post" action="add">
		    <div style="margin-bottom:20px">
				<input class="easyui-textbox" type="text" name="name" style="width:100%" data-options="label:'姓名:',required:true" />
		    </div>
		    <div style="margin-bottom:20px">
				<input class="easyui-datetimebox" name="startTime" style="width:100%;" data-options="label:'开始时间:',required:true">
		    </div>
		    <div style="margin-bottom:20px">
				<input class="easyui-datetimebox" name="endTime" style="width:100%" data-options="label:'结束时间:',required:true" />
		    </div>
		    <div style="margin-bottom:20px">
				<input class="easyui-textbox" name="reason" style="width:100%;height:60px" data-options="label:'请假事由:',multiline:true">
		    </div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交请假</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清除</a>
		</div>
	</div>
	<script>
		function submitForm(){
			$('#ff').form('submit');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>

</body>
</html>