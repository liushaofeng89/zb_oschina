package com.wtkj.rms.projmag.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 档案资料管理，文档上传数据模型
 * 
 * @author service@liushaofeng.cn
 * @datetime 2016-12-7 下午11:31:14
 */
public class PrjArchievesUploadModel {
	// 项目ID
	private String prjId;
	// 文档资料类型
	private int archieveType;
	// 电子档或扫描件
	private MultipartFile archieveScanning;
	// 原件
	private MultipartFile archieveOriginal;
	// 复印件
	private MultipartFile archieveCopy;
	// 备注
	private String note;

	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}

	public int getArchieveType() {
		return archieveType;
	}

	public void setArchieveType(int archieveType) {
		this.archieveType = archieveType;
	}

	public MultipartFile getArchieveScanning() {
		return archieveScanning;
	}

	public void setArchieveScanning(MultipartFile archieveScanning) {
		this.archieveScanning = archieveScanning;
	}

	public MultipartFile getArchieveOriginal() {
		return archieveOriginal;
	}

	public void setArchieveOriginal(MultipartFile archieveOriginal) {
		this.archieveOriginal = archieveOriginal;
	}

	public MultipartFile getArchieveCopy() {
		return archieveCopy;
	}

	public void setArchieveCopy(MultipartFile archieveCopy) {
		this.archieveCopy = archieveCopy;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
