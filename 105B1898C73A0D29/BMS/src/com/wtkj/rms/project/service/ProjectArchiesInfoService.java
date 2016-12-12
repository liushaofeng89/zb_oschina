package com.wtkj.rms.project.service;

import java.util.List;

import com.wtkj.rms.project.model.ProjectArchiesInfoModel;

/**
 * 
 * 档案资料 管理
 * 
 * @author service@liushaofeng.cn
 * @datetime 2016-12-6 下午11:34:02
 */
public interface ProjectArchiesInfoService {

	/**
	 * 通过项目ID查找id相关的档案资料
	 * 
	 * @param id
	 *            项目ID
	 * @return 该项目下的所有文档资料
	 */
	List<ProjectArchiesInfoModel> findById(String id);

	/**
	 * 为项目添加档案资料
	 * 
	 * @param model
	 *            待添加的项目资料模型数据
	 */
	void saveByArchieves(ProjectArchiesInfoModel model);

	/**
	 * 更新资料
	 * @param projectArchiesInfoModel
	 */
	void updateArchieves(ProjectArchiesInfoModel projectArchiesInfoModel);

	/**
	 * 通过ID删除
	 * @param id 待删除的文档ID
	 */
	void remove(String id);
}
