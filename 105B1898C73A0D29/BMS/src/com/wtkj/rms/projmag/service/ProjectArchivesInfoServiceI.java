package com.wtkj.rms.projmag.service;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wtkj.common.PageFilter;
import com.wtkj.rms.projmag.model.ProjectArchivesInfoVo;

public interface ProjectArchivesInfoServiceI {

	public List<ProjectArchivesInfoVo> dataGrid(
			ProjectArchivesInfoVo ProjectAppropriateAccountVo,
			PageFilter ph);

	public Long count(ProjectArchivesInfoVo ProjectAppropriateAccountVo,
			PageFilter ph);

	public ProjectArchivesInfoVo get(Long id);

	public void add(ProjectArchivesInfoVo ProjectAppropriateAccountVo,
			HttpServletRequest request);

	public void edit(ProjectArchivesInfoVo ProjectAppropriateAccountVo,
			HttpServletRequest request);

	public void delete(Long id);

	public void delete(String ids);

	public List<ProjectArchivesInfoVo> findAll(String type);

	public int findMaxTimes();

	public BigInteger countByRegId(Long id);

	BigInteger countByRegIdAndState(Long id, int state);

}
