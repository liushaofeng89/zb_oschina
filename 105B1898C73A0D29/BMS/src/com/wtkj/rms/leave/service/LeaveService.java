package com.wtkj.rms.leave.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wtkj.rms.leave.model.po.LeaveInfoPO;

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
	 * 
	 * @param id
	 *            待销假的ID
	 * @return 销假是否成功
	 */
	public boolean deleteLeaveById(String id);

	/**
	 * 请假批准通过
	 * 
	 * @param id
	 *            待通过的请假ID
	 * @param currentUser
	 *            批准人
	 */
	public void approveById(String id, String currentUser);

	/**
	 * 财务确认通过请假
	 * 
	 * @param id
	 *            待确认通过的请假单ID
	 * @param currentUser
	 *            当前用户
	 */
	public void checkById(String id, String currentUser);

	/**
	 * 通过过滤条件查询
	 * 
	 * @param userName
	 *            待过滤的用户名
	 * @param getsStartTime
	 *            开始时间
	 * @param getsEndTime
	 *            结束时间
	 * @return 过滤后的请假数据
	 */
	public List<LeaveInfoPO> filter(String userName, String getsStartTime,
			String getsEndTime);
}
