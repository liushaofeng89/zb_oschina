<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$('#company').combobox({
		url : "${pageContext.request.contextPath}/dictionary/combox?code=company",
		parentField : 'dictionaryId',
		valueField : 'id',
		value:'${projectBid.company.id}',
		textField : 'text',
		panelHeight : 'auto'				
	});
	
	//标段
	$('#bd').combobox({
		url : "${pageContext.request.contextPath}/dictionary/combox?code=bd",
		parentField : 'dictionaryId',
		valueField : 'id',
		multiple:true,
		textField : 'text',
		panelHeight : '200'				
	});
	
	var selectedBds=[];
	var bss = '${projectBid.bds}';
	selectedBds = bss.split(',');
	$('#bd').combobox('setValues',selectedBds); 
	
	$('#projectMgr').combobox({
		url : "${pageContext.request.contextPath}/dictionary/combox?code=manager",
		parentField : 'dictionaryId',
		valueField : 'id',
		value:'${projectBid.projectMgr.id}',
		textField : 'text',
		panelHeight : '300'				
	});
	
	$('#techniqueMgr').combobox({
		url : "${pageContext.request.contextPath}/dictionary/combox?code=jsjl",
		parentField : 'dictionaryId',
		value:'${projectBid.techniqueMgr.id}',
		valueField : 'id',
		textField : 'text',
		panelHeight : '300'				
	});
	
	$('#aqy').combobox({
		url : "${pageContext.request.contextPath}/certificate/comboxbytype?type=C",
		parentField : 'card_code',
		valueField : 'id',
		value:'${projectBid.aqy.id}',
		textField : 'card_name',
		panelHeight : 'auto'
	});
	
	$('#provice').combobox({
		url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid=&&lvs="+2,
		parentField : 'pid',
		valueField : 'id',
		textField : 'text',
		panelHeight : 300,
		required:true,
		editable:false,//不可编辑，只能选择
		defaultValue:'--请选择--',
		value:'${projectBid.provice.id}',
		onChange:function(provice){
	    	$('#city').combobox({
	    	url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid="+provice+"&&lvs=3",
		    valueField:'id', //值字段
		    textField:'text', //显示的字段
		    panelHeight : 300,
		    required:true,
		    editable:false,//不可编辑，只能选择
		    value:'--请选择--',
		    onChange:function(city,n){
		    	$('#county').combobox({
			    	url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid="+city+"&&lvs=4",
				    valueField:'id', //值字段
				    textField:'text', //显示的字段
				    panelHeight:'auto',
				    required:true,
				    editable:false,//不可编辑，只能选择
				    value:'--请选择--'
				});
	 		}
	    });
	   }
	});
	
	var provice = $('#provice').combobox('getValue');
	$('#city').combobox({
    	url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid="+provice+"&&lvs=3",
    	parentField : 'pid',
	    valueField:'id', //值字段
	    textField:'text', //显示的字段
	    panelHeight : 300,
	    required:true,
	    editable:false,//不可编辑，只能选择
	    defaultValue:'--请选择--',
	    value:'${projectBid.city.id}',
	    onChange:function(city,n){
	    	$('#county').combobox({
		    	url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid="+city+"&&lvs=4",
			    valueField:'id', //值字段
			    textField:'text', //显示的字段
			    panelHeight:'auto',
			    required:true,
			    editable:false,//不可编辑，只能选择
			    value:'--请选择--'
			});
 		}
	});
	
	var city = $('#city').combobox('getValue');
	$('#county').combobox({
    	url : "${pageContext.request.contextPath}/dictionary/xzqhCombox?pid="+city+"&&lvs=4",
    	parentField : 'pid',
	    valueField:'id', //值字段
	    textField:'text', //显示的字段
	    panelHeight:'auto',
	    required:true,
	    editable:false,//不可编辑，只能选择
	    defaultValue:'--请选择--',
	    value:'${projectBid.county.id}'
	});
	
	$('#projectType').combobox({
		url : "${pageContext.request.contextPath}/dictionary/combox?code=projectType",
		parentField : 'dictionaryId',
		valueField : 'text',
		textField : 'text',
		panelHeight : 'auto'
	});	
	var projectType = '${projectBid.projectType}';
	$('#projectType').combobox('setValue', projectType);
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="projectBidEditForm" method="post">
			<table class="grid">
				<tr>
					<th>项目名称 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td colspan="5"><input name="projectName"
						value="${projectBid.projectName}" style="width: 100%;" type="text"
						id="projectName" class="easyui-validatebox span2"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th width="150px">公司名称 &nbsp;</th>
					<td><input type="hidden" name="id" id="id"
						value="${projectBid.id}"></input> <select id="company"
						name="company.id" class="easyui-validatebox span2"
						style="width: 180px;">
					</select></td>
					<th width="120px">标段 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><select id="bd" name="bds"
						class="easyui-validatebox span2" style="width: 150px;"
						data-options="editable:false,required:true">
					</select></td>
					<th>项目类型 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><select id="projectType" name="projectType"
						class="easyui-validatebox span2"
						data-options="editable:false,required:true" style="width: 180px;">
					</select></td>
				</tr>
				<tr>
					<th>地点 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td colspan="5">省：<select id="provice"
						data-options="required:true" name="provice.id"
						class="easyui-validatebox span2" style="width: 140px;">
					</select> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 市：<select id="city"
						name="city.id" class="easyui-validatebox span2"
						style="width: 140px;">
					</select> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 县(区)：<select id="county"
						name="county.id" data-options="required:true"
						class="easyui-validatebox span2" validType="selectValueRequired"
						style="width: 140px;">
					</select>
					</td>
				</tr>
				<tr>
					<th>中标价（元）&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="bid_cost"
						style="width: 100%; height: 100%" type="text" id="bid_cost"
						value="${projectBid.bid_cost}" class="easyui-numberbox" precision="2"
						data-options="required:true" /></td>
						
					<th>中标时间 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input class="Wdate" type="text" name="bidDt"
						value="<fmt:formatDate value="${projectBid.bidDt}" pattern="yyyy-MM-dd"/>"
						id="bidDt" style="width: 98%; height: 100%;"
						data-options="required:true" onfocus="showDate('yyyy-MM-dd')" /></td>
						
					<th width="120px">合同签订时间 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input class="Wdate" type="text" name="signDt" id="signDt" style="width: 98%; height: 100%;"
						value="<fmt:formatDate value="${projectBid.signDt}" pattern="yyyy-MM-dd"/>"
						data-options="required:true" onfocus="showDate('yyyy-MM-dd')" /></td>
				</tr>
				<tr>
					<th>工期结束时间 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input class="Wdate" type="text"
						value="<fmt:formatDate value="${projectBid.duration}" pattern="yyyy-MM-dd"/>"
						name="duration" id="duration"
						style="width: 98%; height: 100%;" data-options="required:true"
						onfocus="showDate('yyyy-MM-dd')" /></td>
						
					<th>管理费比例（%）&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="manageFeeRate"
						style="width: 100%; height: 100%"
						value="${projectBid.manageFeeRate}" type="text" id="manageFeeRate"
						class="easyui-validatebox span2" data-options="required:true" /></td>

					<th width="120px">管理费数额（元）&nbsp;</th>
					<td colspan="3"><input name="manageFee"
						value="${projectBid.manageFee}" style="width: 100%; height: 100%"
						type="text" id="manageFee" class="easyui-numberbox" precision="2"
						disabled="disabled" /></td>

				</tr>
				<tr>
					<th>项目经理&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><select id="projectMgr" name="projectMgr.id"
						class="easyui-validatebox span2" style="width: 180px;"
						data-options="required:true">
					</select></td>
					<th>技术负责人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><select id="techniqueMgr" name="techniqueMgr.id"
						class="easyui-validatebox span2" style="width: 180px;"
						data-options="required:true">
					</select></td>
					<th>专职安全员&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><select id="aqy" name="aqy.id"
						class="easyui-validatebox span2" style="width: 180px;"
						data-options="required:true">
					</select></td>
				</tr>
				<tr>

					<th>施工负责人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="headman" type="text" id="headman"
						style="width: 100%; height: 100%" class="easyui-validatebox span2"
						value="${projectBid.headman}" data-options="required:true" /></td>
					<th>联系方式 &nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="tel" style="width: 100%; height: 100%"
						data-options="required:true" type="text" id="tel"
						value="${projectBid.tel}" class="easyui-validatebox span2" /></td>
					<th>身份证号&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="headmanIdCard" type="text" id="headmanIdCard"
						style="width: 100%; height: 100%" class="easyui-validatebox span2"
						value="${projectBid.headmanIdCard}" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>登记人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="recordman" type="text" readonly
						id="recordman" value="${projectBid.recordman}"
						style="width: 50%; height: 100%" class="easyui-validatebox span2"
						value="${projectBid.recordman}" data-options="required:true" /></td>
						
					<th>发包人&nbsp;<label
						style="color: red; vertical-align: middle; text-align: center;">*</label></th>
					<td><input name="sender" type="text" id="sender" value="${projectBid.sender}"
						style="width: 100%; height: 100%" class="easyui-validatebox span2"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>备注&nbsp;</th>
					<td colspan="5"><textarea style="width: 100%" rows="6"
							name="remark">${projectBid.remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>