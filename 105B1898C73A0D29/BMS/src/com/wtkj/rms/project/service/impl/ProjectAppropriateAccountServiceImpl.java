package com.wtkj.rms.project.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wtkj.common.PageFilter;
import com.wtkj.common.dao.BaseDaoI;
import com.wtkj.common.model.Tdictionary;
import com.wtkj.common.model.Torganization;
import com.wtkj.common.model.Tuser;
import com.wtkj.rms.project.model.ProjectAppropriateAccount;
import com.wtkj.rms.project.model.ProjectAppropriateAccountVo;
import com.wtkj.rms.project.model.ProjectAppropriateReg;
import com.wtkj.rms.project.service.ProjectAppropriateAccountServiceI;

@Service
public class ProjectAppropriateAccountServiceImpl implements
		ProjectAppropriateAccountServiceI {

	@Autowired
	private BaseDaoI<ProjectAppropriateAccount> projectAppropriateAccountDao;

	@Autowired
	private BaseDaoI<ProjectAppropriateReg> projectAppropriateRegDao;

	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Autowired
	private BaseDaoI<Tdictionary> dictionaryDao;

	@Autowired
	private BaseDaoI<Torganization> organizationDao;

	@Override
	public void add(ProjectAppropriateAccountVo vo, HttpServletRequest request) {
		projectAppropriateAccountDao.save(toPo(vo));

	}

	private ProjectAppropriateAccount toPo(ProjectAppropriateAccountVo vo) {
		if (vo == null) {
			return null;
		}
		ProjectAppropriateAccount po = new ProjectAppropriateAccount();
		BeanUtils.copyProperties(vo, po);

		if (vo.getProjectAppRegId() != null && vo.getProjectAppRegId() > 0) {
			ProjectAppropriateReg preg = projectAppropriateRegDao.get(
					ProjectAppropriateReg.class, vo.getProjectAppRegId());
			po.setProjectAppropriateReg(preg);
		}
		if (vo.getApplierId() != null && vo.getApplierId() > 0) {
			Tuser user = userDao.get(Tuser.class, vo.getApplierId());
			po.setApplier(user);

		}
		if (vo.getHandlerId1() != null && vo.getHandlerId1() > 0) {
			Tuser user = userDao.get(Tuser.class, vo.getHandlerId1());
			po.setHandler1(user);
		}
		if (vo.getHandlerId2() != null && vo.getHandlerId2() > 0) {
			Tuser user = userDao.get(Tuser.class, vo.getHandlerId2());
			po.setHandler2(user);
		}
		return po;
	}

	private ProjectAppropriateAccountVo toVo(ProjectAppropriateAccount po) {
		if (po == null) {
			return null;
		}
		ProjectAppropriateAccountVo vo = new ProjectAppropriateAccountVo();
		BeanUtils.copyProperties(po, vo);

		if (po.getProjectAppropriateReg() != null) {
			vo.setProjectAppRegId(po.getProjectAppropriateReg().getId());
			vo.setProjectAppRegName(po.getProjectAppropriateReg()
					.getProjectName());
		}
		if (po.getApplier() != null) {
			vo.setApplierId(po.getApplier().getId());
			vo.setApplierName(po.getApplier().getName());
		}
		if (po.getHandler1() != null) {
			vo.setHandlerId1(po.getHandler1().getId());
			vo.setHandlerIdName1(po.getHandler1().getName());
		}
		if (po.getHandler2() != null) {
			vo.setHandlerId2(po.getHandler2().getId());
			vo.setHandlerIdName2(po.getHandler2().getName());
		}
		return vo;
	}

	@Override
	public void delete(Long id) {
		ProjectAppropriateAccount p = projectAppropriateAccountDao.get(
				ProjectAppropriateAccount.class, id);
		projectAppropriateAccountDao.delete(p);
	}

	@Override
	public void delete(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return;
		}

		String sql = "delete from ProjectAppropriateAccount where id in ("
				+ ids + ")";
		try {
			projectAppropriateAccountDao.executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(ProjectAppropriateAccountVo vo, HttpServletRequest request) {
		projectAppropriateAccountDao.update(toPo(vo));
	}

	@Override
	public ProjectAppropriateAccountVo get(Long id) {
		ProjectAppropriateAccount po = projectAppropriateAccountDao.get(
				ProjectAppropriateAccount.class, id);
		return toVo(po);

	}

	@Override
	public List<ProjectAppropriateAccountVo> dataGrid(
			ProjectAppropriateAccountVo vo, PageFilter ph) {
		List<ProjectAppropriateAccountVo> pl = new ArrayList<ProjectAppropriateAccountVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from ProjectAppropriateAccount t ";
		List<ProjectAppropriateAccount> l = projectAppropriateAccountDao.find(
				hql + whereHql(vo, params) + orderHql(ph), params,
				ph.getPage(), ph.getRows());

		for (ProjectAppropriateAccount t : l) {
			pl.add(toVo(t));
		}
		return pl;
	}

	@Override
	public Long count(ProjectAppropriateAccountVo ProjectAppropriateAccountVo,
			PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from ProjectAppropriateAccount t ";
		return projectAppropriateAccountDao.count("select count(*) " + hql
				+ whereHql(ProjectAppropriateAccountVo, params), params);
	}

	@Override
	public BigInteger countByRegId(Long id) {
		String sql = "select count(*) from projectappropriateaccount t where t.projectAreg_id="
				+ id;
		return projectAppropriateAccountDao.countBySql(sql);
	}

	@Override
	public BigInteger countByRegIdAndState(Long id, int state) {
		String sql = "select count(*) from projectappropriateaccount t where t.state = "
				+ state + " and t.projectAreg_id=" + id;
		return projectAppropriateAccountDao.countBySql(sql);
	}

	private String whereHql(ProjectAppropriateAccountVo vo,
			Map<String, Object> params) {
		String hql = "";
		if (vo != null) {
			hql += " where 1=1 ";

			if (vo.getToAccountDT() != null) {
				hql += " and t.toAccountDT = :toDT";
				params.put("toDT", vo.getToAccountDT());
			}

			if (vo.getProjectAppRegId() != null && vo.getProjectAppRegId() >= 0) {
				hql += " and t.projectAppropriateReg.id = :pregId";
				params.put("pregId", vo.getProjectAppRegId());
			}
		}

		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = " order by t.toAccountDT desc";

		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString += ",t." + ph.getSort() + " " + ph.getOrder();
		}

		if (StringUtils.isEmpty(orderString)) {
			orderString = "  order by t.toAccountDT";
		}

		return orderString;
	}

	@Override
	public List<ProjectAppropriateAccountVo> findAll(String type) {
		List<ProjectAppropriateAccountVo> vos = new ArrayList<ProjectAppropriateAccountVo>();
		String hql = " from ProjectAppropriateAccount t ";
		List<ProjectAppropriateAccount> l = projectAppropriateAccountDao
				.find(hql);
		for (ProjectAppropriateAccount po : l) {
			vos.add(toVo(po));
		}
		return vos;
	}

	@Override
	public int findMaxTimes() {
		// String hql = " select max(times) from ProjectAppropriateAccount t ";
		return 0;
	}

}
