package com.wtkj.rms.genmag.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wtkj.common.PageFilter;
import com.wtkj.common.model.User;
import com.wtkj.rms.genmag.model.PunishNotice;
import com.wtkj.rms.genmag.model.PunishNoticeVo;

public interface PunishNoticeServiceI {
	public List<PunishNotice> dataGrid(User user,
			PunishNoticeVo paramCarRentalReg, PageFilter paramPageFilter);

	public Long count(User user, PunishNoticeVo vo, PageFilter paramPageFilter);

	public PunishNotice get(Long id);

	public Long add(PunishNotice paramCarRentalReg,
			HttpServletRequest paramHttpServletRequest);

	public void edit(PunishNotice paramCarRentalReg,
			HttpServletRequest paramHttpServletRequest);

	public void delete(String paramString);
}
