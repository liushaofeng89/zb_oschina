package com.wtkj.rms.genmag.model;

import java.io.Serializable;
import java.util.Date;

import com.wtkj.rms.process.model.ProcessVo;

public class PunishNoticeVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String usedName;// 用车人
	private Date usedTime;// 用车时间
	private String usedReason;// 用车事由
	private String driver;// 车主
	private String license;// 车号
	private Double startingMileage;// 起点里程
	private Double endMileage;// 终点里程
	private Double drivingMileage;// 行驶里程
	private Double unitPrice;// 单价（元/公里）
	private Double carCost;// 合价（元）
	private Double tolls;// 过路费（元）
	private Double totalCost;// 总计（元）

	private String applicant;
	private Date rentalTimeST;
	private Date rentalTimeET;
	// 处理类型
	private String actionType;

	
	
	// 关联流程
	private ProcessVo process_vo;
	
	
	private String pname;//
	private Date confirmdate;
	private String confirmby;
	private String department;
	private String according;
	private Double pmoney;
	private String reason;
	private String punishby;
	private Date punishdate;
	private String remark;
	
	

	// 查询参数
	private Date punishStartDt;
	private Date punishEndDt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPunishby() {
		return this.punishby;
	}

	public void setPunishby(String argPunishby) {
		this.punishby = argPunishby;
	}
	
	public String getConfirmby() {
		return this.confirmby;
	}

	public void setConfirmby(String argConfirmby) {
		this.confirmby = argConfirmby;
	}

	
	public Date getConfirmdate() {
		return this.confirmdate;
	}

	public void setConfirmdate(Date argConfirmdate) {
		this.confirmdate = argConfirmdate;
	}
	
	public Date getPunishdate() {
		return this.punishdate;
	}

	public void setPunishdate(Date argPunishdate) {
		this.punishdate = argPunishdate;
	}




	public void setPmoney(Double argPmoney) {
		this.pmoney = argPmoney;
	}

	
	public Double getPmoney() {
		return this.pmoney;
	}
	
	public void setPname(String argPnamg) {
		this.pname = argPnamg;
	}

	public String getPname() {
		return this.pname;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(String argDepartment) {
		this.department = argDepartment;
	}

	public String getAccording() {
		return this.according;
	}
	
	public void setAccording(String argAccording) {
		this.according = argAccording;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String argReason) {
		this.reason = argReason;
	}

	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String argRemark) {
		this.remark = argRemark;
	}
	
	public Date getPunishSt() {
		return punishStartDt;
	}

	public void setPunishSt(Date applyStartDt) {
		this.punishStartDt = applyStartDt;
	}
	
	public Date getPunishEt() {
		return punishEndDt;
	}

	public void setPunishEt(Date applyStartDt) {
		this.punishEndDt = applyStartDt;
	}
}
