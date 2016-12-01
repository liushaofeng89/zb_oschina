<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.caption{
	font-size: medium;
    font-style: normal;
    font-weight: bold;
    color: blue;
    margin-top: 20px;
}
</style>

<script type="text/javascript">
	$(function() {
		$('#carRentalRegEditForm').form({
			url : '${pageContext.request.contextPath}/punishNotice/save',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		//审核时提交审核表单
		$('#processForm').form({
			url : '${pageContext.request.contextPath}/punishNotice/complate',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.messager.alert('提示',result.msg,'success');
					var grid = parent.$.modalDialog.openner_dataGrid;
					if(!isEmpty(grid)){
						grid.datagrid('reload');
					}
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		console.log('${viewType}');
	});
	
	$('#startingMileage').bind('input propertychange', function() {
		calc();
	});
	
	$('#endMileage').bind('input propertychange', function() { 
		calc();
	});
	
	$("#unitPrice").bind('input propertychange', function() {
		calc();
	});
	
	$("#tolls").bind('input propertychange', function() {
		calc();
	});
	
	function calc(){
		var endMileage = parseFloat(isEmpty($("#endMileage").val())?0:$("#endMileage").val());
		var startingMileage = parseFloat(isEmpty($("#startingMileage").val())?0:$("#startingMileage").val());
		$("#drivingMileage").val(endMileage - startingMileage);
		var carCost = (parseFloat(isEmpty($("#unitPrice").val())?0:$("#unitPrice").val()) * parseFloat($("#drivingMileage").val())).toFixed(2);
		$("#carCost").val(carCost);
		var totalCost = (parseFloat(isEmpty($("#tolls").val())?0:$("#tolls").val()) + parseFloat($("#carCost").val()==null?0:$("#carCost").val())).toFixed(2);
		$("#totalCost").val(totalCost );
	}
	
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="carRentalRegEditForm" method="post">
			<table class="grid">
				<tr>
					<td colspan="6"><label style="font-weight: bold; color: red;">说明：1、因工作需要租车，必须经公司综合部经理批准备案。2、租车期间的所有安全责任由车主承担，与公司无关。</label>
					</td>
				</tr>
				<tr>
					<th>被处罚人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input type="hidden" name="id" value="${punishNotice.id}" />
						<!-- 动作类型 --> 
						<input type="hidden" name="actionType" id="actionType" value="" /> 
						<input name="pname" value="${punishNotice.pname}" style="width: 100%;" type="text"
						id="usedName" class="easyui-validatebox span2"
						data-options="required:true" validtype="NAME" /></td>

					<th>罚款金额 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input  type="text" name="pmoney"value="${punishNotice.pmoney}"id="pmoney" style="width: 100%; height: 100%"/></td>
				</tr>
				<tr>
					<th>罚款事由&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td colspan="5"><input name="reason"
						value="${punishNotice.reason}" style="width: 100%;"
						type="text" id="reason" class="easyui-validatebox span2"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>罚款依据&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td colspan="5"><input name="according"
						value="${punishNotice.according}" style="width: 100%;"
						type="text" id="reason" class="easyui-validatebox span2"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>处罚人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="punishby" readonly="readonly" value="${punishNotice.punishby}"
						style="width: 100%;" type="text" id="punishby" validtype="NAME"
						class="easyui-validatebox span2" data-options="required:true" /></td>
					<th>处罚时间&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="punishby" readonly="readonly" value="${punishNotice.punishdate}"
						style="width: 100%;" type="text" id="punishdate" validtype="NAME"
						class="easyui-validatebox span2" data-options="required:true" /></td>

				</tr>
				<tr>
					<th>确认人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="confirmby" value="${punishNotice.punishby}"
						style="width: 100%;" type="text" id="confirmby" validtype="NAME"
						class="easyui-validatebox span2" data-options="required:true" /></td>
					<th>确认时间&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="confirmdate" value="${punishNotice.punishdate}"
						style="width: 100%;" type="text" id="confirmdate" validtype="NAME"
						class="easyui-validatebox span2" data-options="required:true" /></td>

				</tr>
				
				
				
			</table>
		</form>

		
	</div>
</div>