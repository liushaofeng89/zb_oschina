package com.wtkj.rms.leave.model.vo;

import com.sun.istack.internal.Nullable;

/**
 * 请假数据模型
 * 
 * @author service@liushaofeng.cn
 */
public class LeaveInfoVO implements java.io.Serializable {

	private static final long serialVersionUID = 5778021914394564786L;

	private int id;
	// 请假人
	private String userName;
	// 请假开始时间
	private String startTime;
	// 请假开始结束
	private String endTime;
	// 请假开始原因
	private String reason;
	// 创建时间
	@Nullable
	private String createTime;
	// 是否已经删除
	private boolean deleted = false;

	public LeaveInfoVO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
