package com.wtkj.rms.pmcc.soft.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wtkj.common.model.IdEntity;
import com.wtkj.common.model.Tuser;
import com.wtkj.rms.process.model.Process;

/**
 * 工资发放登记
 */
@Entity
@Table(name = "payroll_register", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PayrollRegister extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tuser staff;// 关联员工

	private Double basePay; // 基本工资
	private Double agePay; // 工龄工资
	private Double royalty; // 绩效提成
	private Double pensionInsurance; // 养老保险
	private Double injuryInsurance; // 工伤保险
	private Double medicalInsurance; // 医疗保险
	private Double joblessInsurance; // 失业保险
	private Double homeFund; // 住房公积金
	private Double other; // 其它
	private Double shouldPay; // 应发工资
	private Double withhold; // 代扣代缴
	private Double fine; // 罚款
	private Double realPay; // 实发工资

	private String remark; // 备注

	// 关联流程
	private Process process;

	// 工资登记者
	private Tuser creater; // 创建人
	private Date creatTime; // 创建时间

	private String delFlag; // 删除标记

	// 过期状态 0 正常 1过期
	private int expireState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")
	public Tuser getStaff() {
		return staff;
	}

	public void setStaff(Tuser staff) {
		this.staff = staff;
	}

	public Double getBasePay() {
		return basePay;
	}

	public void setBasePay(Double basePay) {
		this.basePay = basePay;
	}

	public Double getAgePay() {
		return agePay;
	}

	public void setAgePay(Double agePay) {
		this.agePay = agePay;
	}

	public Double getRoyalty() {
		return royalty;
	}

	public void setRoyalty(Double royalty) {
		this.royalty = royalty;
	}

	public Double getPensionInsurance() {
		return pensionInsurance;
	}

	public void setPensionInsurance(Double pensionInsurance) {
		this.pensionInsurance = pensionInsurance;
	}

	public Double getInjuryInsurance() {
		return injuryInsurance;
	}

	public void setInjuryInsurance(Double injuryInsurance) {
		this.injuryInsurance = injuryInsurance;
	}

	public Double getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(Double medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public Double getJoblessInsurance() {
		return joblessInsurance;
	}

	public void setJoblessInsurance(Double joblessInsurance) {
		this.joblessInsurance = joblessInsurance;
	}

	public Double getHomeFund() {
		return homeFund;
	}

	public void setHomeFund(Double homeFund) {
		this.homeFund = homeFund;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(Double shouldPay) {
		this.shouldPay = shouldPay;
	}

	public Double getWithhold() {
		return withhold;
	}

	public void setWithhold(Double withhold) {
		this.withhold = withhold;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public Double getRealPay() {
		return realPay;
	}

	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "process_id")
	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creater_id")
	public Tuser getCreater() {
		return creater;
	}

	public void setCreater(Tuser creater) {
		this.creater = creater;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public int getExpireState() {
		return expireState;
	}

	public void setExpireState(int expireState) {
		this.expireState = expireState;
	}

}
