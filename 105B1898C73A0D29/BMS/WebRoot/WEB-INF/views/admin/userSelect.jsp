<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/user/dataGrid',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			//fitColumns:true,
			fit : true,
			idField : 'id',
			pageSize : 50,
			pageList : [ 10, 20, 30 ],
			columns : [ [ {
				width : '100',
				title : '登录名',
				field : 'loginname',
				align : 'center',
				sortable : true
			}, {
				width : '100',
				title : '姓名',
				align : 'center',
				field : 'name',
				sortable : true
			}, {
				width : '100',
				title : '部门ID',
				field : 'organizationId',
				hidden : true
			}, {
				width : '100',
				align : 'center',
				title : '所属部门',
				field : 'organizationName'
			}, {
				width : '100',
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
				width : '100',
				title : '年龄',
				align : 'center',
				field : 'age',
				sortable : true
			} ] ],
			toolbar : '#toolbar'
		});
	});

	// 查询
	function searchFun() {
		var queryParams = $('#dataGrid').datagrid('options').queryParams;
		queryParams.name = "";
		queryParams.organizationName = "";
		var name = $('#name').val();
		var organizationName = $('#organizationName').val();
		queryParams.name = isEmpty(name) ? "" : name;
		queryParams.organizationName = isEmpty(organizationName) ? "" : organizationName;
		//重新加载datagrid的数据
		reloadGrid($("#dataGrid"));
	}
	// 
	function clearFun() {
		$('#name').val('');
		$('#organizationName').val('');
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" class="mygrid-toolbar" style="inline: true">
		姓名：<input type="text" name=name id="name" style="height: 100%" /> 
		部门：<input type="text" name=organizationName id="organizationName"style="height: 100%" /> 
			<a onclick="searchFun();"href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon_toolbar_search'">搜索</a> 
			
			<a onclick="clearFun();" href="javascript:void(0);"class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon_toolbar_clear'">清空</a>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
