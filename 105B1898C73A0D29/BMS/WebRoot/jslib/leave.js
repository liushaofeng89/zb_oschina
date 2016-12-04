var dataGrid;

var id = -1;
var createTime = "";
var deleted = "false";

function addLeave() {
	id = -1;
	createTime = "";
	deleted = "false";
	
	$("#userName").textbox("setValue","");
	$("#startTime").datetimebox("setValue","");
	$("#endTime").datetimebox("setValue","");
	$("#reason").textbox("setValue","");
	$('#dlg').dialog('open');
}

function editLeave() {
	var row = $('#dataGrid').datagrid('getSelected');
	if (row) {
		id = row.id;
		createTime = row.createTime;
		deleted = row.deleted;
		
		$("#userName").textbox("setValue",row.userName);
		$("#startTime").datetimebox("setValue",row.startTime);
		$("#endTime").datetimebox("setValue",row.endTime);
		$("#reason").textbox("setValue",row.reason);
	}
	$('#dlg').dialog('open');
}

function rmvSelected() {
	var ids = [];
	var rows = $('#dataGrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
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

function submitForm() {
	$.ajax({
		type : "POST",
		url : "add",
		data : "userName=" + $("#userName").val() + "&startTime="
				+ $('#startTime').datebox('getValue') + "&endTime="
				+ $('#endTime').datebox('getValue') + "&reason="
				+ $("#reason").val() + "&id=" + id + "&createTime=" + createTime
				+ "&deleted=" + deleted,
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
			width : '200',
			title : '请假原因',
			sortable : true,
			align : 'center',
			field : 'reason',
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
});