package com.wtkj.rms.leave.model.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.wtkj.rms.leave.model.vo.LeaveInfoVO;

/**
 * 请假模型
 * 
 * @author service@liushaofeng.cn
 */
@Entity
@Table(name = "leave_info", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LeaveInfoPO {

	public static final int INVALID_ID = -1;
	/** 该请假是否已经删除：是 */
	public static final boolean LEAVE_STATUS_DELETED_TRUE = true;
	/** 该请假是否已经删除：否 */
	public static final boolean LEAVE_STATUS_DELETED_FALSE = false;

	private int id;
	// 请假人
	private String userName;
	// 请假开始时间
	private Date startTime;
	// 请假开始结束
	private Date endTime;
	// 请假开始原因
	private String reason;
	// 创建时间
	private Date createTime = Calendar.getInstance().getTime();
	// 是否已经删除
	private boolean deleted = false;

	/**
	 * default constructor
	 */
	public LeaveInfoPO() {

	}

	/**
	 * VO to PO
	 * @param info VO model
	 * @throws ParseException
	 */
	public LeaveInfoPO(LeaveInfoVO info) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (INVALID_ID != info.getId()) {
			this.id = info.getId();
		}
		this.userName = info.getUserName();
		this.startTime = sdf.parse(info.getStartTime());
		this.endTime = sdf.parse(info.getEndTime());
		this.reason = info.getReason();
		this.deleted = info.isDeleted();
		if (!info.getCreateTime().trim().isEmpty()) {
			this.createTime = sdf.parse(info.getCreateTime());
		}
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "ent_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "leave_reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}