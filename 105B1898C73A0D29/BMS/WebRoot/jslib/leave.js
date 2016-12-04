var dataGrid;

var id = -1;
var createTime = "";
var limitTime = "";
var leaveType = -1;

/**
 * 添加请假
 */
function addLeave() {
	id = -1;
	createTime = "";
	limitTime = "";

	$("#startTime").datetimebox("setValue", "");
	$("#endTime").datetimebox("setValue", "");
	$("#limitTime").textbox("setValue", "");
	$("#leaveType").combobox("setValue", "");
	$('#dlg').dialog('open');
}

/**
 * 修改请假
 */
function editLeave() {
	var row = $('#dataGrid').datagrid('getSelected');
	if (row) {
		id = row.id;
		createTime = row.createTime;
		limitTime = row.limitTime;

		$("#userName").textbox("setValue", row.userName);
		$("#startTime").datetimebox("setValue", row.startTime);
		$("#endTime").datetimebox("setValue", row.endTime);
		$("#limitTime").textbox("setValue", row.limitTime);
		$("#leaveType").combobox("setValue", row.leaveType);
	}
	$('#dlg').dialog('open');
}

/**
 * 批准人批准
 */
function approve(){
	var ids = [];
	var rows = $('#dataGrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.messager.confirm("销假提示", "您确定要批准勾选的这些请假吗？", function (data) {
        if (data) {
        	$.ajax({
        		type : "POST",
        		url : "approveByIds",
        		data : "ids=" + JSON.stringify(ids),
        		success : function(result) {
        			var data = eval('(' + result + ')');
        			if (data.success) {
        				$.messager.alert('批准请假成功', data.msg, 'info');
        				window.location.reload();
        			} else {
        				$.messager.alert('批准请假部分成功或失败', data.msg, 'error');
        			}
        		}
        	});
        }
    });
}

/**
 * 财务核对确认
 */
function check() {
	var ids = [];
	var rows = $('#dataGrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.messager.confirm("财务确认提示", "您确定这些请假吗？", function (data) {
        if (data) {
        	$.ajax({
        		type : "POST",
        		url : "check",
        		data : "ids=" + JSON.stringify(ids),
        		success : function(result) {
        			var data = eval('(' + result + ')');
        			if (data.success) {
        				$.messager.alert('确认请假成功', data.msg, 'info');
        				window.location.reload();
        			} else {
        				$.messager.alert('确认请假部分成功或失败', data.msg, 'error');
        			}
        		}
        	});
        }
    });
}

/**
 * 删除请假
 */
function rmvSelected() {
	var ids = [];
	var rows = $('#dataGrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.messager.confirm("销假提示", "您确定要为勾选的用户销假吗？", function (data) {
        if (data) {
        	$.ajax({
        		type : "POST",
        		url : "removeByIds",
        		data : "ids=" + JSON.stringify(ids),
        		success : function(result) {
        			var data = eval('(' + result + ')');
        			if (data.success) {
        				$.messager.alert('销假成功', data.msg, 'info');
        				window.location.reload();
        			} else {
        				$.messager.alert('销假部分成功或失败', data.msg, 'error');
        			}
        		}
        	});
        }
    });
}

function submitForm() {
	$.ajax({
		type : "POST",
		url : "add",
		data : "userName=" + $("#userName").val() + "&startTime="
				+ $('#startTime').datebox('getValue') + "&endTime="
				+ $('#endTime').datebox('getValue') + "&limitTime="
				+ $("#limitTime").datebox('getValue') + "&id=" + id
				+ "&createTime=" + createTime + "&leaveType="
				+ $("#leaveType").combobox("getValue"),
		success : function(result) {
			var data = eval('(' + result + ')');
			if (data.success) {
				$.messager.alert('请假保存', '请假数据保存成功！', 'info');
				window.location.reload();
			} else {
				$.messager.alert('请假保存', data.msg, 'error');

			}
		}
	});
}

function clearForm() {
	$('#ff').form('clear');
}

$(document).ready(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : 'findAll',
		striped : true,
		pagination : true,
		nowrap : true,
		singleSelect : false,
		pageSize : getDefaultPageSize(),
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		columns : [ [ {
			checkbox : true,
			field : 'id',
			width : '30'
		}, {
			title : '序号',
			field : 'index',
			align : 'center',
			width : '40',
			formatter : function(value, row, index) {
				return index + 1;
			}
		}, {
			width : '100',
			title : '用户名',
			align : 'center',
			field : 'userName'
		}, {
			width : '130',
			title : '开始时间',
			align : 'center',
			field : 'startTime'
		}, {
			width : '130',
			title : '结束时间',
			align : 'center',
			field : 'endTime'
		}, {
			width : '130',
			title : '请假类型',
			align : 'center',
			field : 'leaveType',
			formatter : function(value, row, index) {
				if (value != null) {
					switch (value) {
					case 1:
						return '事假';
					case 2:
						return '病假';
					case 3:
						return '婚假';
					case 4:
						return '产假';
					case 5:
						return '丧假';
					}
				}
			}
		}, {
			width : '130',
			title : '规定时间',
			align : 'center',
			field : 'limitTime'
		}, {
			width : '130',
			title : '批准人',
			align : 'center',
			field : 'approver'
		}, {
			width : '130',
			title : '批准时间',
			align : 'center',
			field : 'approveTime'
		}, {
			width : '130',
			title : '财务确认',
			align : 'center',
			field : 'financer'
		}, {
			width : '130',
			title : '创建时间',
			sortable : true,
			align : 'center',
			field : 'createTime'
		} ] ],
		toolbar : '#toolbar'
	});

	$('#dlg').dialog('close');
	$('#check').dialog('close');
});