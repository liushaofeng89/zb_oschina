<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/plugins/datagrid-detailview.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/easyui1.3.3/plugins/datagrid-statistics.js" charset="utf-8"></script>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>工程档案管理</title>
<style>
	.combo-arrow{
		height: 20px !important;
	}
	#selectPrj tr,#selectPrj td:nth(1){
		width: 100%;
	}
	td{
		padding: 5px;
	}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" class="mygrid-toolbar" style="inline: true">
		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/projectArchives/add')}">
				<a onclick="selectPrj();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_add'">添加 </a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_add_disabled'"><font
					color="gray">添加</font> </a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/projectArchives/edit')}">
				<a onclick="viewFun('edit');" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_edit'">编辑</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_edit_disabled'"><font
					color="gray">编辑</font> </a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/projectArchives/delete')}">
				<a onclick="deleteFun();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_del'">删除 </a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_del_disabled'"><font
					color="gray">删除</font> </a>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/projectArchives/detail')}">
				<a onclick="viewFun('detail');" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail'">详情 </a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail_disabled'"><font
					color="gray">详情</font> </a>
			</c:otherwise>
		</c:choose>
		
		<a onclick="addArchieves();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_update'">新增档案</a>
		
		
		<a onclick="printFun(0);" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_detail'">打印预览</a> <a
			onclick="printFun(1);" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_detail'">导出Excel</a>
		
		<a onclick="searchFun();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_search'">搜索</a> 
					<a onclick="clearFun();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_clear'">清空</a>

		<table>
			<tr>
				<th>项目名称:</th>
				<td><input style="width: 150px" type="text" id="projectName"></td>
				<th>收款人:</th>
				<td><input style="width: 100px" type="text" id="payee"></td>
				<th>联系人:</th>
				<td><input style="width: 100px" type="text" id="contactName"></td>
			</tr>
		</table>
	</div>
	
	<div id="selectPrj" class="easyui-dialog" title="拉取项目" data-options="iconCls:'icon-save',resizable:true,modal:true" style="width:800px;height:600px;padding:10px">
		<table>
			<tr>
				<th>项目名称(输入关键词后回车):</th>
				<td>
					<input class="easyui-textbox" onkeyup="loadKeywordsPrj(this)" style="width:100%;">
				</td>
			</tr>
		</table>
		<table id="selectedOne" data-options="fit:true,border:false"></table>
	</div>
	
	<div id="sdlg" class="easyui-dialog" title="项目新增档案" data-options="iconCls:'icon-save'" style="width:500px;height:350px;padding:10px">
		<form action="addPrjArchieves" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td style="text-align: right">项目名称:<input type="hidden" id="prjId" name="prjId"/> </td>
					<td style="text-align: left" id="prjName" colspan="2"></td>
				</tr>
				<tr>
					<td style="text-align: right">文档类型:</td>
					<td style="text-align: left" colspan="2">
						<select class="easyui-combobox" id="archieveType" name="archieveType" >
							<option value="1">招标文件</option>
							<option value="2">投标文件</option>
							<option value="3">中标通知书</option>
							<option value="4">施工合同</option>
							<option value="5">施工资料</option>
							<option value="6">施工照片</option>
							<option value="7">施工影响</option>
							<option value="8">拨款资料</option>
							<option value="9">竣工资料</option>
							<option value="10">验收资料</option>
				    	</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">电子版或扫描件:</td>
					<td style="text-align: left" colspan="2">
						<input type="file" class="easyui-filebox" id="archieveScanning" name="archieveScanning">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">原件:</td>
					<td style="text-align: left">
						<input class="easyui-textbox easyui-tooltip" title="原件编号" id="archieveOriginalNo" name="archieveOriginalNo">
					</td>
					<td style="text-align: left">
						<input class="easyui-textbox easyui-tooltip" title="原件存放位置" id="archieveOriginalPath" name="archieveOriginalPath">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">复印件:</td>
					<td style="text-align: left">
						<input class="easyui-textbox easyui-tooltip" title="复印件编号" id="archieveCopyNo" name="archieveCopyNo">
					</td>
					<td style="text-align: left">
						<input class="easyui-textbox easyui-tooltip" title="复印件存放位置" id="archieveCopyPath" name="archieveCopyPath">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">备注:</td>
					<td style="text-align: left" colspan="2">
						<textarea id="note" name="note" style="height:60px;width:100%"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<button type="submit">确认添加</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		 var prjCtx = '${ctx}';
	</script>
	<script type="text/javascript" src="${ctx}/jslib/projectArchives.js" charset="utf-8"></script>
</body>
</html>