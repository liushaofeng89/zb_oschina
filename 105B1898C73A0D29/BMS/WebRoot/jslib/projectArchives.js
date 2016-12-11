var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : 'dataGrid',
		striped : true,
		pagination : true,
		nowrap : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : getDefaultPageSize(),
		view: detailview,   
	    detailFormatter:function(index,row){   
	        return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';   
	    },
	    onExpandRow: function(index,row){ 
	        $('#ddv-'+index).datagrid({ 
	            url:'../projectAppropriateAccount/dataGrid', 
	            queryParams:{
	            	projectAppRegId : row.id
	            },
	            striped : true,
	            singleSelect:true, 
	            loadMsg:'', 
	            height:'auto', 
	            showFooter : true,
	            columns : [ [ {
					title : '序号',
					field : 'id',
					align : 'center',
					width : '40',
					rowspan:2,
					formatter : function(val, row, index) {
						return index + 1;
					}
				},{
					title : '名称',
					field : 'archivesType',
					align : 'center',
					width : '80',
					rowspan:2,
					formatter : function(val, row, index) {
						if (val != null) {
							switch (val) {
							case 1:
								return '招标文件';
							case 2:
								return '投标文件';
							case 3:
								return '中标通知书';
							case 4:
								return '施工合同';
							case 5:
								return '施工资料';
							case 6:
								return '施工照片';
							case 7:
								return '施工影响';
							case 8:
								return '拨款资料';
							case 9:
								return '竣工资料';
							case 10:
								return '验收资料';
							}
						}
					}
				}, 
				{
					title : '<font color="red">电子版或扫描件</font>',
					align : 'center'
				}, 
				{
					title : '<font color="red">原件</font>',
					colspan : 2,
					align : 'center'
				}, 
				{
					title : '<font color="blue">复印件</font>',
					colspan : 2,
					align : 'center'
				}, {
					width : '90',
					rowspan : 2,
					title : '备注',
					align : 'center',
					field : 'archivesNote'
					
				} ] , [ {
					width : 200,
					title : '下载',
					align : 'center',
					field : 'archivesScanningPath',
					formatter : function(value,row,index){
						return "<a href='download?path="+value+"'>点击下载</a>";
					}
				}, {
					width : 110,
					title : '编号',
					align : 'center',
					field : 'archivesOriginalNo',
					sum : false
				}, {
					width : 200,
					title : '存放位置',
					sortable : true,
					align : 'center',
					field : 'archivesOriginalPath'
				}, {
					width : '110',
					title : '编号',
					rowspan : 2,
					align : 'center',
					field : 'archivesCopyNo'
				}, {
					width : 200,
					title : '存放位置',
					sortable : true,
					align : 'center',
					field : 'archivesCopyPath',
					sum : false
				} ] ],
	            onResize:function(){ 
	                $('#dataGrid').datagrid('fixDetailRowHeight',index); 
	            }, 
	            onLoadSuccess:function(){ 
	                setTimeout(function(){ 
	                    $('#dataGrid').datagrid('fixDetailRowHeight',index); 
	                },0); 
	                $('#ddv-'+index).datagrid('statistics');
	            } 
	        }); 
	        $('#dataGrid').datagrid('fixDetailRowHeight',index); 
	    },
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		columns : [ [ {
			checkbox : true,
			field : 'id',
			rowspan : 2,
			width : '50',
		}, {
			title : '序号',
			rowspan : 2,
			field : 'index',
			align : 'center',
			width : '50',
			formatter : function(value, row, index) {
				return index + 1;
			}
		}, {
			width : '300',
			title : '项目名称',
			rowspan : 2,
			align : 'center',
			field : 'projectName'
		}, {
			width : '120',
			title : '中标价（元）',
			rowspan : 2,
			sortable : true,
			align : 'center',
			field : 'bidPrice'
		}, {
			width : '120',
			title : '管理费比例(%)',
			rowspan : 2,
			sortable : true,
			align : 'center',
			field : 'managerFeeRate'
		}, {
			width : '120',
			title : '管理费数额(元)',
			rowspan : 2,
			sortable : true,
			align : 'center',
			field : 'managerFee'
		}, {
			width : '120',
			title : '中标日期',
			rowspan : 2,
			sortable : true,
			align : 'center',
			field : 'bidDT',
			formatter : Common.formatter
		}, {
			width : '120',
			title : '合同工期(天)',
			rowspan : 2,
			align : 'center',
			field : 'contractDuration'
		},   {
			width : '280',
			title : '备注',
			rowspan : 2,
			align : 'center',
			field : 'remark'
		} ] ],
		toolbar : '#toolbar'
	});

});

function selectPrj(){
	var param = {"filter" :""};
	doLoad(param);
	$('#selectPrj').dialog('open');
}

function loadKeywordsPrj(obj){
	if (event.keyCode==13)
	{ 
		var param = {"filter" :$(obj).val()};
		doLoad(param);
	} 
}

function doLoad(param){
	$("#selectedOne").datagrid({
		title:'中标项目',
	    width:550,
	    height:200,
	    url:'loadFilteredPrjs',
	    queryParams : param,
	    singleSelect : true,
	    pageSize : getDefaultPageSize(),
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
	    columns:[[
	        {checkbox : true,	field : 'id',width : '30'},
	        {field:'projectName',title:'项目名称',width:200},
	        {field:'bid_cost',title:'中标价(元)',width:60},
	        {field:'bidDt',title:'中标日期',width:80,align:'right'},
	        {field:'unitcost',title:'合同工期（天）',width:80,align:'right'},
	        {field:'manageFeeRate',title:'管理费比例（%）',width:100},
	        {field:'manageFee',title:'管理费数额（元）',width:60},
	        {field:'status',title:'户名',width:60},
	        {field:'status',title:'开户行',width:60},
	        {field:'status',title:'账号',width:60},
	        {field:'headman',title:'姓名',width:60},
	        {field:'tel',title:'电话',width:60},
	        {field:'headmanIdCard',title:'身份证号',width:60},
	        {field:'remark',title:'备注',width:60},
	    ]],

	    toolbar:[{
	        text:'添加勾选的项目',
	        iconCls:'icon_toolbar_add',
	        handler:function(){
	        	var ids = [];
	        	var rows = $('#selectedOne').datagrid('getSelected');
	        	ids.push(rows.id);
	        	$.ajax({
            		type : "POST",
            		url : "selectedPrjs",
            		data : "ids=" + JSON.stringify(ids),
            		success : function(result) {
            			var data = eval('(' + result + ')');
            			if (data.success) {
            				$.messager.alert('拉取项目成功！', data.msg, 'info');
            				$('#selectPrj').dialog('close');
            				$("#dataGrid").datagrid("reload") 
            			} else {
            				$.messager.alert('拉取项目失败！', data.msg, 'error');
            			}
            		}
            	});
	        }
	    }]

	});
}

function searchFun() {
	var queryParams = $('#dataGrid').datagrid('options').queryParams;
	queryParams.projectName = "";
	queryParams.payee = "";
	queryParams.contactName = "";
	var projectName = $('#projectName').val();
	var payee = $('#payee').val();
	var contactName = $('#contactName').val();
	if (!isEmpty(projectName)) {
		queryParams.projectName = projectName;
	}
	if (!isEmpty(payee)) {
		queryParams.payee = payee;
	}
	if (!isEmpty(contactName)) {
		queryParams.contactName = contactName;
	}
	//重新加载datagrid的数据  
	reloadGrid($("#dataGrid"));
}

function clearFun() {
	$('#projectName').val('');
	$('#payee').val('');
	$('#contactName').val('');
	$('#state').combobox('clear');
}
	
//统一处理 add,edit,detail,handler
function viewFun(viewType) {
	var me = this;
	var title = '', href = prjCtx+ '/projectArchives/viewPage?viewType='+ viewType;
	var buttons = [];
	if ('add' == viewType) {
		title = '联营工程档案登记';
		buttons = [ {
			text : '登记',
			id:'paregBtn',
			handler : me.submitForm
		}, {
			text : '退出',
			handler : me.closeDialog
		} ];
	} else {
		var rows = dataGrid.datagrid('getSelections');
		if (rows == null || rows.length == 0) {
			parent.$.messager.alert('警告', '没有可操作对象!');
			return;
		}

		if (rows.length > 1) {
			parent.$.messager.alert('警告', '只能操作一条记录!');
			return;
		}

		var id = rows[0].id;
		href += '&id=' + id;
		//编辑
		if ('edit' == viewType) {
			title = '联营工程档案修改';
			buttons = [ {
				text : '修改',
				id:'paregBtn',
				handler : me.submitForm
			}, {
				text : '退出',
				handler : me.closeDialog
			} ];
		} else if ('detail' == viewType) {
			title = '联营工程档案详情';
			buttons = [ {
				text : '退出',
				handler : me.closeDialog
			} ];
		}
	}

	parent.$.modalDialog({
		title : title,
		width : 900,
		height : 630,
		resizable : true,
		href : href,
		buttons : buttons
	});
}

//提交form
function submitForm() {
	//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	var f = parent.$.modalDialog.handler.find('#projectAppropriateRegViewForm');
	f.submit();
}

//关闭窗口
function closeDialog() {
	parent.$.modalDialog.handler.dialog('close');
}

function deleteFun() {
	var selected = getSelected();
	if (isEmpty(selected)) {
		parent.$.messager.alert('警告', '至少选中一条记录!');
		return;
	}
	parent.$.messager.confirm('询问', '确认删除选中的记录吗？', function(b) {
		if (b) {
			progressLoad();
			$.post('delete', {
				ids : selected
			}, function(result) {
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

function printFun(type) {
	var id = null;
	var rows = dataGrid.datagrid('getSelections');
	if (rows == null || rows.length == 0) {
		parent.$.messager.alert('警告', '没有可查看对象!');
		return;
	}
	if (rows.length > 1) {
		parent.$.messager.alert('警告', '只能对一条记录查看!');
		return;
	}
	id = rows[0].id;
	var url = ctxPath + "/report/projectAReg?id=" + id+"&&type="+type;
	if(type == 0){
		var iWidth = 700; //弹出窗口的宽度;
		var iHeight = 600; //弹出窗口的高度;
		id = rows[0].id;
		//获得窗口的垂直位置
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		//获得窗口的水平位置
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var tmp = window.open(url, "_blank", "width=700,height=600,top=" + iTop
				+ ",left=" + iLeft);
		tmp.focus();
	}else{
		var tmp = window.open(url);
		tmp.focus();
	}
}
	
function addArchieves(){
	var rows = $('#dataGrid').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('Warning','添加档案资料至少得勾选一个项目！');
		return;
	}
	
	if(rows.length>1){
		$.messager.alert('Warning','同一时刻只能为一个项目添加档案资料！');
		return;
	}
	$("#prjName").text(rows[0].projectName);
	$("#prjId").val(rows[0].id);
	$('#sdlg').dialog('open');
}

$(document).ready(function(){
	$('#sdlg').dialog('close');
	$('#selectPrj').dialog('close');
});