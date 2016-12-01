package com.wtkj.rms.genmag.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wtkj.common.model.IdEntity;
import com.wtkj.rms.process.model.Process;
import com.wtkj.common.model.Tuser;

@Entity
@Table(name = "punishnotice", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PunishNotice extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pname;//
	private Date confirmdate;
	private Tuser confirmby;
	private String department;
	private String according;
	private Double pmoney;
	private String reason;
	private Tuser punishby;
	private Date punishdate;
	private String remark;
	

	// 关联流程
	private Process process;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "process_id")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "punishby")
	public Tuser getPunishby() {
		return punishby;
	}
	

	public void setPunishby(Tuser argPunishby) {
		this.punishby = argPunishby;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "confirmby")
	public Tuser getConfirmby() {
		return this.confirmby;
	}

	public void setConfirmby(Tuser argConfirmby) {
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

	


	
}
