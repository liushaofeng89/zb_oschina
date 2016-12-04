<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.wtkj.common.GlobalConstant"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/jslib/leave.js" charset="utf-8"></script>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>请假申请</title>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" class="easyui-datagrid" data-options="singleSelect:true,method:'get',fit:true,border:false"></table>
	</div>
	
	<div id="toolbar" class="mygrid-toolbar" style="inline: true">

		<a onclick="addLeave();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_add'">请假</a>

		<a onclick="editLeave()" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_edit'">编辑</a>

		<a onclick="rmvSelected();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_del'">销假 </a>
			
		<a onclick="approve();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_ok'">批准 </a>
			
		<a onclick="check();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_save'">确认 </a>

	</div>
	
	<div id="dlg" class="easyui-dialog" title="请假申请" data-options="iconCls:'icon-save'" style="width:600px;height:350px;padding:10px">
		<form id="ff" >
		    <div style="margin-bottom:20px">
				<input class="easyui-textbox" id="userName" name="userName" style="width:100%;" data-options="label:'请假人:',required:true" value="${sessionInfo.name}">
			</div>
		    <div style="margin-bottom:20px">
				<input class="easyui-datetimebox" id="startTime" name="startTime" style="width:100%;" data-options="label:'开始时间:',required:true">
		    </div>
		    <div style="margin-bottom:20px">
				<input class="easyui-datetimebox" id="endTime" name="endTime" style="width:100%" data-options="label:'结束时间:',required:true" />
		    </div>
		    <div style="margin-bottom:20px">
		    	<select class="easyui-combobox" id="leaveType" name="leaveType" style="width:100%;" data-options="label:'请假种类:'">
					<option value="1">事假</option>
					<option value="2">病假</option>
					<option value="3">婚假</option>
					<option value="4">产假</option>
					<option value="5">丧假</option>
		    	</select>
		    </div>
		    <div style="margin-bottom:20px">
				<input class="easyui-datetimebox" id="limitTime" name="limitTime" style="width:100%" data-options="label:'规定时间:'" />
		    </div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交请假</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清除</a>
		</div>
	</div>
</body>
</html>