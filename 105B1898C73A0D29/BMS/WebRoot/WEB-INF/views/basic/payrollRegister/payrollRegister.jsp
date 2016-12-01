<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.wtkj.common.GlobalConstant"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctx}/jslib/easyui1.3.3/plugins/datagrid-groupview.js"
	charset="utf-8"></script>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>工资发放登记</title>
<script type="text/javascript">
	var dataGrid;
	$(function () {
		dataGrid = $('#dataGrid').datagrid({
				url : '${ctx}' + '/payrollRegister/dataGrid',
				striped : true,
				pagination : true,
				nowrap : true,
				idField : 'id',
				sortName : 'id',
				sortOrder : 'desc',
				pageSize : getDefaultPageSize(),
				pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
				columns : [[{
							checkbox : true,
							field : 'id',
							width : '30',
						}, {
							title : '序号',
							field : 'index',
							align : 'center',
							width : '40',
							formatter : function (value, row, index) {
								return index + 1;
							}
						}, {
							width : '120',
							title : '月份',
							sortable : true,
							align : 'center',
							field : 'creatTime',
							formatter : Common.formatterMonth
						}, {
							width : '120',
							title : '姓名',
							sortable : true,
							align : 'center',
							field : 'name'
						}, {
							width : '90',
							title : '基本工资',
							sortable : true,
							align : 'center',
							field : 'basePay'
						}, {
							width : '90',
							title : '工龄工资',
							sortable : true,
							align : 'center',
							field : 'agePay'
						}, {
							width : '90',
							title : '绩效提成',
							align : 'center',
							field : 'royalty'
						}, {
							width : '90',
							title : '养老保险',
							align : 'center',
							field : 'pensionInsurance'
						}, {
							width : '90',
							title : '工伤保险',
							align : 'center',
							field : 'injuryInsurance'
						}, {
							width : '90',
							title : '医疗保险',
							align : 'center',
							field : 'medicalInsurance'
						}, {
							width : '90',
							title : '失业保险',
							align : 'center',
							field : 'joblessInsurance'
						}, {
							width : '90',
							title : '住房公积金',
							align : 'center',
							field : 'homeFund'
						}, {
							width : '90',
							title : '其它',
							align : 'center',
							field : 'other'
						}, {
							width : '90',
							title : '应发工资',
							align : 'center',
							field : 'shouldPay'
						}, {
							width : '90',
							title : '代扣代缴',
							align : 'center',
							field : 'withhold'
						}, {
							width : '90',
							title : '罚款',
							align : 'center',
							field : 'fine'
						}, {
							width : '90',
							title : '实发工资',
							align : 'center',
							field : 'realPay'
						}, {
							width : '150',
							title : '备注',
							align : 'center',
							field : 'remark'
						}, {
							width : '120',
							title : '状态',
							sortable : true,
							align : 'center',
							field : 'process_vo',
							formatter : function (value, row, index) {
								if(value == "0"){
									return "编制中";
								}else if(value == "1"){
									return '<font color="green">已编制</font>';
								}else if(value == "2"){
									return '<font color="green">员工已确认</font>';
								}else if(value == "-2"){
									return '<font color="green">员工退回</font>';
								}else if(value == "3"){
									return '<font color="green">工资管理员已确认</font>';
								}else if(value == "-3"){
									return '<font color="green">工资管理员退回</font>';
								}else if(value == "4"){
									return '<font color="green">总经理已确认</font>';
								}else if(value == "-4"){
									return '<font color="green">总经理退回</font>';
								}else if(value == "5"){
									return '<font color="red">会计已确认</font>';
								}else if(value == "-5"){
									return '<font color="red">会计退回</font>';
								}else if(value == "6"){
									return '<font color="red">出纳已确认</font>';
								}else if(value == "-6"){
									return '<font color="red">出纳退回</font>';
								}
							}
						},
					]],
	
				toolbar : '#toolbar'
			});
	
	});
	// 查询
	function searchFun() {
		var queryParams = $('#dataGrid').datagrid('options').queryParams;
		queryParams.creatTime = "";
		var creatTime = $('#creatTime').val();
		queryParams.creatTime = isEmpty(creatTime) ? "" : creatTime;
		//重新加载datagrid的数据
		reloadGrid($("#dataGrid"));
	}
	// 
	function clearFun() {
		$('#creatTime').val('');
	}
	
	//统一处理 add,edit,detail,handler
	function viewFun(viewType) {
		var me = this,dwidth=1000,dheight=500;
		var title = '', href = '${ctx}/payrollRegister/viewPage?viewType='+ viewType;
		var buttons = [];
		if ('add' == viewType) {
			title = '工资编制登记';
			buttons = [ {
				text : '保存',
				handler : function(){
					parent.$.modalDialog.handler.find('#actionType').val('<%=GlobalConstant.ACTION_ADD%>');
					me.submitForm();
				}
			
			},{
				text : '提交',
				handler : function() {
					parent.$.messager.confirm('提醒','提交后不能修改,确认提交？',function(b) {
						if (b) {
							parent.$.modalDialog.handler.find('#actionType').val('<%=GlobalConstant.ACTION_COMMIT%>');
							me.submitForm();
						}else{
							return;
						}
					});
				}
			}, {
				text : '退出',
				handler : me.closeDialog
			} ];
		} else {
			var rows = dataGrid.datagrid('getSelections');
			if (rows == null || rows.length == 0) {
				parent.$.messager.alert('警告', '至少选择一条记录!');
				return;
			}

			if (rows.length > 1) {
				parent.$.messager.alert('警告', '只能查看一条记录!');
				return;
			}

			var id = rows[0].id;
			var state = rows[0].process_vo == null?'':rows[0].process_vo.state;
			href += '&id=' + id;
			//编辑
			if ('edit' == viewType) {
				if (state == 1) {
					parent.$.messager.alert('警告', '已提交不可修改!');
					return;
				}
				if (state == 2) {
					parent.$.messager.alert('警告', '流程结束不可修改!');
					return;
				}
				title = '工资编制修改';
				buttons = [ {
					text : '编辑',
					handler : function(){
						parent.$.modalDialog.handler.find('#actionType').val('<%=GlobalConstant.ACTION_EDIT%>');
						me.submitForm();
					}
				},{
					text : '提交',
					handler : function() {
						parent.$.messager.confirm('提醒','提交后不能修改,确认提交？',function(b) {
							if (b) {
								parent.$.modalDialog.handler.find('#actionType').val('<%=GlobalConstant.ACTION_COMMIT%>');
								me.submitForm();
							}else{
								return;
							}
						});
					}
				}, {
					text : '退出',
					handler : me.closeDialog
				} ];
			} else if ('detail' == viewType) {
				title = '工资详情';
				buttons = [ {
					text : '退出',
					handler : function() {
						me.closeDialog();
					}
				} ];
			} else if ('audit' == viewType) {//审核
				dwidth = document.body.clientWidth * 0.7;
				dheight = document.body.clientHeight * 0.6;
				if (state <= 0) {
					parent.$.messager.alert('警告', '申请人尚未提交,不可办理!');
					return;
				}
				if (state == 6 || state == '6') {
					parent.$.messager.alert('警告', '流程已结束不可审核!');
					return;
				}
				title = getTitleByState(state);
				buttons = [
						{
							text : '通过',
							handler : function() {
								var f = parent.$.modalDialog.handler
										.find('#processForm');
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								parent.$.modalDialog.handler.find('#option').val(0);
								f.submit();
							}
						},{
							text : '退回',
							handler : function() {
								var f = parent.$.modalDialog.handler
										.find('#processForm');
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								parent.$.modalDialog.handler.find('#option').val(1);
								f.submit();
							}
						}, {
							text : '退出',
							handler : closeDialog
						} ];
			} else if ('processChart' == viewType){
				href = '${ctx}/payrollRegister/processChart?id='+ id;
				title = '工资编制流程图';
				dwidth = document.body.clientWidth * 0.8;
				dheight = document.body.clientHeight * 0.9;
				buttons = [ {
					text : '退出',
					handler : function() {
						me.closeDialog();
					}
				} ];
			}
		}
		
		parent.$.modalDialog({
			title : title,
			width : dwidth,
			height : dheight,
			resizable : true,
			href : href,
			buttons : buttons
		});
	}
	
	function getTitleByState(state){
		var title = '';
		if(state > 0){
			if(state == 1){
				title = '员工确认';
			}else if(state == 2){
				title = '工资管理员确认';
			}else if(state == 3){
				title = '总经理确认';
			}else if(state == 4){
				title = '会计确认';
			}else if(state == 5){
				title = '出纳确认';
			}
		}
		return title;
	}
	
	//提交form
	function submitForm() {
		//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
		parent.$.modalDialog.openner_dataGrid = dataGrid;
		var f = parent.$.modalDialog.handler.find('#carRentalRegEditForm');
		f.submit();
	}

	//关闭窗口
	function closeDialog() {
		parent.$.modalDialog.handler.dialog('close');
	}
	
	// 删除
	function deleteFun() {
		var selected = getSelected();
		if (isEmpty(selected)) {
			parent.$.messager.alert('警告', '至少选中一条记录!');
			return;
		}
		parent.$.messager.confirm('询问', '确认删除选中的记录吗？', function (b) {
			if (b) {
				progressLoad();
				$.post('${ctx}/payrollRegister/delete', {
					ids : selected
				}, function (result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						//删除成功后,前台删除行,防止下次再删除的时候可以取到之前选到的行
						removeSelectedRow(dataGrid);
						dataGrid.datagrid('reload');
					} else {
						parent.$.messager.alert('警告', result.msg, 'warning');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	
	// 打印导出
	function printFun(type) {
		var creatTime = $('#creatTime').val();
		if(creatTime != null && creatTime != ""){
			var url = ctxPath + "/report/payrollRegister?type=" + type + "&creatTime=" + creatTime;
			if(type == 0){
				var tmp = window.open (
						url,
						'newwindow',
						'width='+(window.screen.availWidth-10)+
						',height='+(window.screen.availHeight-30)+ 
						',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'
				);
				tmp.focus();
			}else{
				var tmp = window.open(url);
				tmp.focus();
			}
		}else{
			parent.$.messager.alert('警告', '请选择日期!');
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" class="mygrid-toolbar" style="inline: true">

		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/add')}">
				<a onclick="viewFun('<%=GlobalConstant.PAGE_TYPE_ADD%>');" href="javascript:void(0);"
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
				test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/edit')}">
				<a onclick="viewFun('<%=GlobalConstant.PAGE_TYPE_EDIT%>');" href="javascript:void(0);"
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
				test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/delete')}">
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
				test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/detail')}">
				<a onclick="viewFun('<%=GlobalConstant.PAGE_TYPE_DETAIL%>');" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail'">详情 </a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail_disabled'"><font
					color="gray">详情</font> </a>
			</c:otherwise>
		</c:choose>
		
		<a onclick="viewFun('processChart');" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail'">查看流程</a>
		
		<c:choose>
			<c:when
				test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/approval')}">
				<a onclick="viewFun('audit');" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail'">审核</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail_disabled'"><font
					color="gray">审核</font> </a>
			</c:otherwise>
		</c:choose>

		<a onclick="printFun(1);" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_detail'">导出Excel</a>
		<a onclick="printFun(0);" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon_toolbar_detail'">打印预览 </a> 
		<a onclick="searchFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_search'">搜索</a> 
		<a onclick="clearFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon_toolbar_clear'">清空</a>

		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/payrollRegister/search')}">
			<table>
				<tr>
					<th>日期:</th>
					<td>
						<input class="Wdate" type="text" name=creatTime id="creatTime" style="height: 100%"
						onfocus="showDate('yyyy-MM')" /> 
					</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>