package com.wtkj.rms.genmag.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wtkj.common.PageFilter;
import com.wtkj.common.dao.BaseDaoI;
import com.wtkj.common.model.Tdictionary;
import com.wtkj.common.model.User;
import com.wtkj.rms.genmag.model.PunishNotice;
import com.wtkj.rms.genmag.model.PunishNoticeVo;
import com.wtkj.rms.genmag.service.impl.PunishNoticeServiceI;
import com.wtkj.rms.process.model.ProcessVo;

@Service
public class PunishNoticeServiceImpl implements PunishNoticeServiceI {

	@Autowired
	private BaseDaoI<PunishNotice> punishNoticeDao;

	@Autowired
	private BaseDaoI<Tdictionary> dictionaryDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PunishNotice> dataGrid(User user, PunishNoticeVo p,
			PageFilter ph) {
		Map params = new HashMap();
		String hql = " from PunishNotice t ";
		List l = punishNoticeDao.find(hql + whereHql(user, p, params)
				+ orderHql(ph), params, ph.getPage(), ph.getRows());
		return l;
	}

	public Long count(User user, PunishNoticeVo p, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from PunishNotice t ";
		return punishNoticeDao.count(
				"select count(*) " + hql + whereHql(user, p, params), params);
	}

	private String whereHql(User user, PunishNoticeVo p,
			Map<String, Object> params) {
		String hql = " where 1=1 ";
		if (p != null) {
			
			
			
			// 业务查询
			if (!(StringUtils.isEmpty(p.getPname()))) {
				hql = hql + " and t.pname like :applicant";
				params.put("pname", "%%" + p.getPname() + "%%");
			}

			if (p.getPunishSt() != null) {
				hql = hql + " and t.punishdate >= :rentalTimeST";
				params.put("rentalTimeST", p.getPunishSt());
			}

			if (p.getPunishEt() != null) {
				hql = hql + " and t.punishdate <= :rentalTimeET";
				params.put("rentalTimeET", p.getPunishEt());
			}
		}
		return hql;
	}

	public PunishNotice get(Long id) {
		PunishNotice crr = null;
		if (id != null && id > 0) {
			crr = punishNoticeDao.get(PunishNotice.class, Long.valueOf(id));
		}
		return crr;
	}

	public Long add(PunishNotice p, HttpServletRequest request) {
		return (Long) punishNoticeDao.save(p);
	}

	public void edit(PunishNotice p, HttpServletRequest request) {
		this.punishNoticeDao.update(p);
	}

	public void delete(String ids) {
		String sqlids = "";
		if ((!(StringUtils.isEmpty(ids))) && (ids.length() > 0)) {
			String[] idArray = ids.split(",");
			if (idArray.length == 1)
				sqlids = "'" + idArray[0] + "'";
			else {
				for (int i = 0; i < idArray.length; ++i) {
					String id = idArray[i];
					sqlids = sqlids
							+ ((i == idArray.length - 1) ? "'" + id + "'"
									: new StringBuilder("'").append(id)
											.append("',").toString());
				}
			}
		}
		String sql = "delete from punishnotice where id in (" + sqlids + ")";
		this.punishNoticeDao.executeSql(sql);
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}
}
