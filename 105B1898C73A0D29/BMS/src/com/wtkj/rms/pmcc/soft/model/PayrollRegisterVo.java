package com.wtkj.rms.pmcc.soft.model;

import java.io.Serializable;
import java.util.Date;

import com.wtkj.rms.process.model.ProcessVo;

/**
 * 工资发放登记
 */
public class PayrollRegisterVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String staffName;
	private Long staffId;
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
	private ProcessVo process_vo;

	// 工资登记者
	private String createrName; // 创建人
	private Long createrId; // 创建人
	private Date creatTime; // 创建时间

	private String delFlag; // 删除标记

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public ProcessVo getProcess_vo() {
		return process_vo;
	}

	public void setProcess_vo(ProcessVo process_vo) {
		this.process_vo = process_vo;
	}

}
