package com.wtkj.rms.projmag.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wtkj.common.PageFilter;
import com.wtkj.rms.projmag.model.ProjectArchives;

public interface ProjectArchivesServiceI {

	public List<ProjectArchives> dataGrid(ProjectArchives p,
			PageFilter ph);

	public Long count(ProjectArchives p, PageFilter ph);

	public ProjectArchives get(Long id);

	public void add(ProjectArchives p, HttpServletRequest request);

	public void edit(ProjectArchives p, HttpServletRequest request);

	public void delete(String ids);

	public void batchUpdateState(String ids, int i);
}
