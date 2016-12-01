<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
	<script type="text/javascript">
	var dataGrid;
	var organizationTree;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/user/dataGrid',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createdatetime',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '登录名',
				field : 'loginname',
				align : 'center',
				sortable : true
			}, {
				width : '60',
				title : '姓名',
				align : 'center',
				field : 'name',
				sortable : true
			},{
				width : '80',
				title : '部门ID',
				field : 'organizationId',
				hidden : true
			},{
				width : '80',
				align : 'center',
				title : '所属部门',
				field : 'organizationName'
			}] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				align : 'center',
				field : 'createdatetime',
				sortable : true
			},  {
				width : '50',
				title : '性别',
				align : 'center',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '男';
					case 1:
						return '女';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				align : 'center',
				field : 'age',
				sortable : true
			}, {
				width : '80',
				title : '用户类型',
				field : 'usertype',
				align : 'center',
				sortable : true,
				formatter : function(value, row, index) {
					var jsonObjs = $.parseJSON('${usertypeJson}');
					for(var i =0;i<jsonObjs.length;i++){
						var jsonobj = jsonObjs[i];
						if(jsonobj.id==value){
							return jsonobj.text;
						}
					}
					return "未知类型";
				}
			},{
				width : '160',
				title : '角色',
				field : 'roleNames',
				sortable : false
			}] ],
			toolbar : '#toolbar'
		});
	});
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" class="mygrid-toolbar" style="inline: true">
		<a onclick="viewFun('<%=GlobalConstant.PAGE_TYPE_ADD%>');"
			href="javascript:void(0);" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_add'">添加 </a> 
		<a
			onclick="viewFun('<%=GlobalConstant.PAGE_TYPE_ADD%>');"
			href="javascript:void(0);" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_add'">添加 </a>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>