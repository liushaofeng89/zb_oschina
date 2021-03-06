package com.wtkj.rms.leave.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtkj.common.dao.BaseDaoI;
import com.wtkj.rms.leave.model.po.LeaveInfoPO;
import com.wtkj.rms.leave.service.LeaveService;

/**
 * 请假业务处理实现类
 * 
 * @author service@liushaofeng.cn
 * 
 */
@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private BaseDaoI<LeaveInfoPO> leaveDao;

	@Override
	public void add(LeaveInfoPO leaveInfo, HttpServletRequest paramHttpServletRequest) {
		leaveDao.saveOrUpdate(leaveInfo);
	}

	@Override
	public List<LeaveInfoPO> findAll() {
		return leaveDao.find("from LeaveInfoPO l where l.deleted = false order by l.createTime desc");
	}

	@Override
	public boolean deleteLeaveById(String id) {
		int executeSql = leaveDao.executeSql("update leave_info l set l.is_deleted = 1 where l.id = '" + id + "'");
		return 1 == executeSql ? true : false;
	}

	@Override
	public void approveById(String id, String currentUser) {
		leaveDao.executeSql("update leave_info l set l.approver = '" + currentUser
				+ "',approve_time = now() where l.id = '" + id + "'");
	}

	@Override
	public void checkById(String id, String currentUser, float money) {
		leaveDao.executeSql("update leave_info l set l.financer = '" + currentUser + "',l.cost_money = " + money
				+ " where l.id = '" + id + "'");
	}

	@Override
	public List<LeaveInfoPO> filter(String userName, String startTime, String endTime) {
		if (null == userName || userName.trim().isEmpty()) {
			if (startTime.trim().isEmpty()) {
				return leaveDao.find("from LeaveInfoPO l where l.createTime<='" + endTime + "'");
			}

			if (endTime.trim().isEmpty()) {
				return leaveDao.find("from LeaveInfoPO l where l.createTime >='" + startTime + "'");
			}
			return leaveDao.find("from LeaveInfoPO l where l.createTime<='" + endTime + "' and l.createTime >='"
					+ startTime + "'");
		} else {
			if (startTime.trim().isEmpty() && endTime.trim().isEmpty()) {
				return leaveDao.find("from LeaveInfoPO l where l.userName = '" + userName + "'");
			}

			if (startTime.trim().isEmpty()) {
				return leaveDao.find("from LeaveInfoPO l where l.userName = '" + userName + "' and l.createTime<='"
						+ endTime + "'");
			}

			if (endTime.trim().isEmpty()) {
				return leaveDao.find("from LeaveInfoPO l where l.userName = '" + userName + "' and l.createTime >='"
						+ startTime + "'");
			}
		}
		return leaveDao.find("from LeaveInfoPO l where l.userName = '" + userName + "' and l.createTime<='" + endTime
				+ "' and l.createTime >='" + startTime + "'");
	}
}
