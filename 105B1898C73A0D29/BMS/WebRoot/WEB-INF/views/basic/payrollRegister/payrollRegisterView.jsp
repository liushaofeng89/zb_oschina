<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function(){
		$('#payrollRegisterEditForm').form({
			url : '${pageContext.request.contextPath}/payrollRegister/apply',
			onSubmit : function() {
				// 表单验证
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
					parent.$.messager.alert('提示', result.msg, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
	
	registerListener();
	
	function registerListener(){
		$('#basePay').bind('input propertychange', function() {
			calc();
		});
		$('#agePay').bind('input propertychange', function() {
			calc();
		});
		$('#royalty').bind('input propertychange', function() {
			calc();
		});
		$('#pensionInsurance').bind('input propertychange', function() {
			calc();
		});
		$('#injuryInsurance').bind('input propertychange', function() {
			calc();
		});
		$('#medicalInsurance').bind('input propertychange', function() {
			calc();
		});
		$('#joblessInsurance').bind('input propertychange', function() {
			calc();
		});
		$('#homeFund').bind('input propertychange', function() {
			calc();
		});
		$('#other').bind('input propertychange', function() {
			calc();
		});
		$('#withhold').bind('input propertychange', function() {
			calc();
		});
		$('#fine').bind('input propertychange', function() {
			calc();
		});
	}
	
	function calc(){
		var add1 = isEmpty($('#basePay').val()) ? 0 : $('#basePay').val();
		var add2 = isEmpty($('#agePay').val()) ? 0 : $('#agePay').val();
		var add3 = isEmpty($('#royalty').val()) ? 0 : $('#royalty').val();
		var n1 = isEmpty($('#pensionInsurance').val()) ? 0 : $('#pensionInsurance').val();
		var n2 = isEmpty($('#injuryInsurance').val()) ? 0 : $('#injuryInsurance').val();
		var n3 = isEmpty($('#medicalInsurance').val()) ? 0 : $('#medicalInsurance').val();
		var n4 = isEmpty($('#joblessInsurance').val()) ? 0 : $('#joblessInsurance').val();
		var n5 = isEmpty($('#homeFund').val()) ? 0 : $('#homeFund').val();
		var n6 = isEmpty($('#other').val()) ? 0 : $('#other').val();
		
		var n7 = isEmpty($('#withhold').val()) ? 0 : $('#withhold').val();
		var n8 = isEmpty($('#fine').val()) ? 0 : $('#fine').val();
		var shouldPay = parseFloat(add1)+parseFloat(add2)+parseFloat(add3)-parseFloat(n1)-parseFloat(n2)
		-parseFloat(n3)-parseFloat(n4)-parseFloat(n5)-parseFloat(n6);
		var realPay = shouldPay - parseFloat(n7)-parseFloat(n8);
		$('#shouldPay').val(shouldPay);
		$('#realPay').val(realPay);
	}
	
	function selectUser(){
		debugger;
		parent.$.modalDialogTwo({
			title : '选择用户',
			width : 500,
			height : 500,
			resizable : true,
			href : '${ctx}/user/userSelectPage.do',
			buttons : [
				{
					text : '选择',
					handler : function() {
						var dataGrid = parent.$.modalDialogTwo.handler.find('#dataGrid');
						var rows = dataGrid.datagrid('getSelections');
						if (rows == null || rows.length == 0) {
							parent.$.messager.alert('警告', '至少选择一条记录!');
							return;
						}

						if (rows.length > 1) {
							parent.$.messager.alert('警告', '只能选择一条记录!');
							return;
						}
						
						var user = rows[0];
						$('#staffName').val(user.name);
						$('#staffId').val(user.id);
						
					}
				}, {
					text : '退出',
					handler : function(){
						parent.$.modalDialog.handler.dialog('close');
					}
				}
			]
		});
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="payrollRegisterEditForm" method="post">
			<!-- 主键 -->
			<input id="id" name="id" type="hidden" value="${payrollRegister.id}" />
			<input id="creatTime" name="creatTime" type="hidden" value="${payrollRegister.creatTime}"/>
			<input id="creater" name="createrId" type="hidden" value="${payrollRegister.createrId}" />
			<input id="delFlag" name="delFlag" type="hidden" value="${payrollRegister.delFlag}" />
			
			<c:if test="${payrollRegister.process_vo != null}">
				<input type="hidden" name="process_vo.id" value="${payrollRegister.process_vo.id}" />
			</c:if> 
			<!-- 动作类型 --> 
			<input type="hidden" name="actionType" id="actionType" value="" /> 
			
			<table class="grid">
				<tr>
					<th>
						姓名 &nbsp;<label style="color: red; vertical-align: middle; text-align: center;">*</label>
					</th>
					<td>
						<input id="staffName" name="staffName" type="text" style="width: 100%;" onclick="selectUser()"
							 class="easyui-validatebox span2" value="${payrollRegister.staffName}" 
							 data-options="required:true" />
						<input type="hidden" name="staffId" id="staffId" value="${payrollRegister.staffId}" /> 
					</td>
					<th>
						基本工资 &nbsp;<label style="color: red; vertical-align: middle; text-align: center;">*</label>
					</th>
					<td>
						<input name="basePay" id="basePay" type="text" style="width: 100%;"  
							 class="easyui-numberbox" value="${payrollRegister.basePay}" 
							 precision=2 data-options="required:true" />
					</td>
					<th>
						工龄工资 &nbsp;<label style="color: red; vertical-align: middle; text-align: center;">*</label>
					</th>
					<td>
						<input name="agePay" id="agePay" type="text" style="width: 100%;"  
							value="${payrollRegister.agePay}"
							class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>
						绩效提成 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="royalty" id="royalty" type="text" style="width: 100%;"  
							value="${payrollRegister.royalty}"
							class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						养老保险 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="pensionInsurance" id="pensionInsurance" type="text" style="width: 100%;"  
							value="${payrollRegister.pensionInsurance}"
							class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						工伤保险 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="injuryInsurance" id="injuryInsurance" type="text" style="width: 100%;"  
							 class="easyui-numberbox" value="${payrollRegister.injuryInsurance}"
							 precision=2 data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>
						医疗保险 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="medicalInsurance" id="medicalInsurance" type="text" style="width: 100%;"  
							 value="${payrollRegister.medicalInsurance}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						失业保险 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="joblessInsurance" id="joblessInsurance" type="text" style="width: 100%;"  
							 value="${payrollRegister.joblessInsurance}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						住房公积金 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="homeFund" id="homeFund" type="text" style="width: 100%;"  
							 value="${payrollRegister.homeFund}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>
						其它 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="other" id="other" type="text" style="width: 100%;"  
							 value="${payrollRegister.other}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						应发工资 &nbsp;<label style="color: red; vertical-align: middle; text-align: center;">*</label>
					</th>
					<td>
						<input name="shouldPay" id="shouldPay" type="text" style="width: 100%;"  
							 value="${payrollRegister.shouldPay}"
							 class="disabled" readonly="readonly" precision=2 data-options="required:true" />
					</td>
					<th>
						代扣代缴 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="withhold" id="withhold" type="text" style="width: 100%;"  
							 value="${payrollRegister.withhold}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>
						罚款 &nbsp;<label style="vertical-align: middle; text-align: center;"></label>
					</th>
					<td>
						<input name="fine" id="fine" type="text" style="width: 100%;"  
							 value="${payrollRegister.fine}"
							 class="easyui-numberbox" precision=2 data-options="required:true" />
					</td>
					<th>
						实发工资 &nbsp;<label style="color: red; vertical-align: middle; text-align: center;">*</label>
					</th>
					<td>
						<input name="realPay" id="realPay" type="text" style="width: 100%;"  
							 value="${payrollRegister.realPay}"
							 class="disabled" readonly="readonly" precision=2 data-options="required:true" />
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<th>备注&nbsp;</th>
					<td colspan="5">
						<textarea style="width: 100%" rows="2" name="remark">${payrollRegister.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
		
		<c:if test="${fn:contains(viewType, 'handler')}">
			<form id="processForm" method="post">
				<table class="grid">
					<caption align="top" class="caption">流程审核</caption>
					<tr>
						<th>流程名称 &nbsp;</th>
						<td>
							<input type="hidden" name="id" value="${payrollRegister.process_vo.id}" /> 
							<input type="hidden" name="option" id="option" value="" type="text" />
							<input name="processName" value="${payrollRegister.process_vo.processName}"
								type="text" id="processName" style="width: 100%; height: 100%"
								class="easyui-validatebox span2" readonly="readonly" />
						</td>
						<th>申请人 &nbsp;</th>
						<td>
							<input type="hidden" name="applyUserId" value="${payrollRegister.process_vo.applyUserId}" /> 
							<input name="applyUserName" value="${payrollRegister.process_vo.applyUserName}" type="text"
								id="applyUserName" style="width: 100%; height: 100%"
								class="easyui-validatebox span2" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>开始时间 &nbsp;</th>
						<td><input class="Wdate" type="text" name="startDT"
							readonly="readonly" style="width: 98%; height: 100%;"
							value="${payrollRegister.process_vo.startDT}" /></td>
						<th>结束时间 &nbsp;</th>
						<td><input class="Wdate" type="text" name="endDT"
							readonly="readonly" value="${payrollRegister.process_vo.endDT}"
							id="endDT" style="width: 98%; height: 100%;" /></td>
					</tr>
					<tr>
						<th>办理意见&nbsp;</th>
						<td colspan="4">
							<textarea style="width: 100%" rows="3"
								name="remark" id="remark">${payrollRegister.process_vo.remark}</textarea>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
</div>