package com.wtkj.rms.leave.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wtkj.rms.leave.model.po.LeaveInfoPO;
import com.wtkj.rms.leave.model.vo.LeaveInfoVO;

/**
 * 请假业务接口定义，CRUD相关接口定义
 * 
 * @author Administrator
 * 
 */
public interface LeaveService {

	/**
	 * 新增请假
	 * 
	 * @param leaveInfo
	 *            请假信息数据模型
	 * @param paramHttpServletRequest
	 *            HttpServletRequest
	 */
	public void add(LeaveInfoPO leaveInfo,
			HttpServletRequest paramHttpServletRequest);

	/**
	 * 查询所有的请假列表
	 * 
	 * @return 所有的请假列表
	 */
	public List<LeaveInfoPO> findAll();

	
	/**
	 * 销假
	 * @param id 待销假的ID
	 * @return 销假是否成功
	 */
	public boolean deleteLeaveById(String id);
}
