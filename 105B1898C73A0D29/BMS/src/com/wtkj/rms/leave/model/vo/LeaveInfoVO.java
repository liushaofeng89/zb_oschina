package com.wtkj.rms.leave.model.vo;

import java.util.Date;

/**
 * 请假数据模型
 * @author service@liushaofeng.cn
 */
public class LeaveInfoVO implements java.io.Serializable {

	private static final long serialVersionUID = 5778021914394564786L;
	
	private int id;
	// 请假人
	private String userName;
	// 请假开始时间
	private Date startTime;
	// 请假开始结束
	private Date endTime;
	// 请假开始原因
	private String reason;
	
	

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
