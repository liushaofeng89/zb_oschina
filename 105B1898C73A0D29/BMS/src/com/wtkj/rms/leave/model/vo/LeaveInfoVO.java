package com.wtkj.rms.leave.model.vo;

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
	// 请假类型：1表示事假，2表示病假，3表示婚嫁，4表示产假，5表示丧假'
	private int leaveType;
	// 规定时间
	private String limitTime;
	// 批准人
	private String approver;
	// 批准时间
	private String approveTime;
	// 财务确认
	private String financer;
	// 创建时间
	private String createTime;
	// 是否已经删除
	private boolean deleted;

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

	public int getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getFinancer() {
		return financer;
	}

	public void setFinancer(String financer) {
		this.financer = financer;
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
