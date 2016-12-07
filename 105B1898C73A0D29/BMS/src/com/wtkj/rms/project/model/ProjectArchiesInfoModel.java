package com.wtkj.rms.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 档案资料
 * 
 * @author service@liushaofeng.cn
 */
@Entity
@Table(name = "project_archives_info", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ProjectArchiesInfoModel {

	private int id;
	// 工程ID，用于资料绑定
	private int prjId;
	/*
	 * 档案资料类型： 1.招标文件 2.投标文件 3.中标通知书 4.施工合同 5.施工资料 6.施工照片 7.施工影响 8.拨款资料 9.竣工资料
	 * 10.验收资料
	 */
	private int archivesType;
	// 电子版或扫描件存放路径
	private String archivesScanningPath;
	// 原件编号
	private String archivesOriginalNo;
	// 原件存放路径
	private String archivesOriginalPath;
	// 复印件
	private String archivesCopyNo;
	// 复印件存放路径
	private String archivesCopyPath;
	// 备注
	private String archivesNote;

	public ProjectArchiesInfoModel() {

	}

	public ProjectArchiesInfoModel(int prjId, int archivesType, String scanningFilePath, String originalFileNo,
			String originalFilePath, String copyFileNo, String copyFilePath, String note) {
		this.prjId = prjId;
		this.archivesType = archivesType;
		this.archivesScanningPath = scanningFilePath;
		this.archivesOriginalNo = originalFileNo;
		this.archivesOriginalPath = originalFilePath;
		this.archivesCopyNo = copyFileNo;
		this.archivesCopyPath = copyFilePath;
		this.archivesNote = note;
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

	@Column(name = "prj_id")
	public int getPrjId() {
		return prjId;
	}

	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}

	@Column(name = "archives_type")
	public int getArchivesType() {
		return archivesType;
	}

	public void setArchivesType(int archivesType) {
		this.archivesType = archivesType;
	}

	@Column(name = "archives_scanning_path")
	public String getArchivesScanningPath() {
		return archivesScanningPath;
	}

	public void setArchivesScanningPath(String archivesScanningPath) {
		this.archivesScanningPath = archivesScanningPath;
	}

	@Column(name = "archives_original_path")
	public String getArchivesOriginalPath() {
		return archivesOriginalPath;
	}

	public void setArchivesOriginalPath(String archivesOriginalPath) {
		this.archivesOriginalPath = archivesOriginalPath;
	}

	@Column(name = "archives_copy_path")
	public String getArchivesCopyPath() {
		return archivesCopyPath;
	}

	public void setArchivesCopyPath(String archivesCopyPath) {
		this.archivesCopyPath = archivesCopyPath;
	}

	@Column(name = "archives_note")
	public String getArchivesNote() {
		return archivesNote;
	}

	public void setArchivesNote(String archivesNote) {
		this.archivesNote = archivesNote;
	}

	@Column(name = "archives_original_no")
	public String getArchivesOriginalNo() {
		return archivesOriginalNo;
	}

	public void setArchivesOriginalNo(String archivesOriginalNo) {
		this.archivesOriginalNo = archivesOriginalNo;
	}

	@Column(name = "archives_copy_no")
	public String getArchivesCopyNo() {
		return archivesCopyNo;
	}

	public void setArchivesCopyNo(String archivesCopyNo) {
		this.archivesCopyNo = archivesCopyNo;
	}

}
