package com.wtkj.rms.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtkj.common.dao.BaseDaoI;
import com.wtkj.rms.project.model.ProjectArchiesInfoModel;
import com.wtkj.rms.project.service.ProjectArchiesInfoService;

/**
 * 
 * 项目文档查询实现接口
 * 
 * @author service@liushaofeng.cn
 * @datetime 2016-12-6 下午11:36:05
 */
@Service
public class ProjectArchiesInfoServiceImpl implements ProjectArchiesInfoService {

	@Autowired
	private BaseDaoI<ProjectArchiesInfoModel> archievesInfoDao;

	@Override
	public List<ProjectArchiesInfoModel> findById(String id) {
		return archievesInfoDao.find("from ProjectArchiesInfoModel p where p.prjId = " + id
				+ " order by p.archivesType asc");
	}

	@Override
	public void saveByArchieves(ProjectArchiesInfoModel model) {
		archievesInfoDao.saveOrUpdate(model);
	}

	@Override
	public void updateArchieves(ProjectArchiesInfoModel model) {
		archievesInfoDao.update(model);
	}

}
