package com.wtkj.rms.leave.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtkj.common.GlobalConstant;
import com.wtkj.common.Json;
import com.wtkj.common.SessionInfo;
import com.wtkj.common.controller.BaseController;
import com.wtkj.rms.leave.model.po.LeaveInfoPO;
import com.wtkj.rms.leave.model.vo.LeaveFilterModel;
import com.wtkj.rms.leave.model.vo.LeaveInfoVO;
import com.wtkj.rms.leave.service.LeaveService;

/**
 * 员工请假控制器
 * 
 * @version 1.0.0
 * @author service@liushaofeng.cn
 * 
 */
@Controller
@RequestMapping("/leave")
public class LeaverController extends BaseController {

	@Autowired
	private LeaveService leaveService;

	/**
	 * 发起请假需求
	 * 
	 * @return 请求结果
	 */
	@RequestMapping({ "/manager" })
	public String manager(HttpServletRequest request) {
		return "/basic/leave/request";
	}

	/**
	 * 发起请假需求
	 * 
	 * @return 请求结果
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json addLeave(LeaveInfoVO info, HttpServletRequest request) {
		Json j = new Json();
		try {
			leaveService.add(new LeaveInfoPO(info), request);
			j.setSuccess(true);
			j.setMsg("请假申请成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 通过ID批准请假
	 * 
	 * @param ids
	 *            待批准的请假单
	 * @param request
	 *            HttpServletRequest
	 * @return 批准请假成功与否
	 */
	@RequestMapping("/approveByIds")
	@ResponseBody
	public Json approveByIds(String ids, HttpServletRequest request) {
		Json j = new Json();
		String[] idList = getIds(ids);
		for (String string : idList) {
			leaveService.approveById(string, getCurrentUser(request));
		}
		j.setSuccess(true);
		j.setMsg("更新数据库成功！");
		return j;
	}

	private String getCurrentUser(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		return sessionInfo.getName();
	}

	/**
	 * 财务确认请假
	 * 
	 * @param ids
	 *            待核对的请假单
	 * @param request
	 *            HttpServletRequest
	 * @return 确认请假成功与否
	 */
	@RequestMapping("/check")
	@ResponseBody
	public Json check(String ids, String money, HttpServletRequest request) {
		Json j = new Json();
		String[] idList = getIds(ids);
		for (String string : idList) {
			leaveService.checkById(string, getCurrentUser(request), Float.parseFloat(money));
		}
		j.setSuccess(true);
		j.setMsg("更新数据库成功！");
		return j;
	}

	/**
	 * 通过ID销假
	 * 
	 * @return 销假成功与否
	 */
	@RequestMapping("/removeByIds")
	@ResponseBody
	public Json removeByIds(String ids) {
		Json j = new Json();
		String[] idList = getIds(ids);
		for (String string : idList) {
			leaveService.deleteLeaveById(string);
		}
		j.setSuccess(true);
		j.setMsg("更新数据库成功！");
		return j;
	}

	private String[] getIds(String ids) {
		if (null != ids && !ids.trim().isEmpty()) {
			return ids.substring(1, ids.length() - 1).split(",");
		}
		return new String[0];
	}

	/**
	 * 过滤请假
	 * 
	 * @param model
	 * @return 过滤后的请假
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/filter")
	@ResponseBody
	public List<LeaveInfoPO> filter(LeaveFilterModel model) throws UnsupportedEncodingException {
		String userName = new String(model.getsName().getBytes("iso-8859-1"), "UTF-8");
		return leaveService.filter(userName, model.getsStartTime(), model.getsEndTime());
	}

	/**
	 * 发起请假需求
	 * 
	 * @return 请求结果
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<LeaveInfoPO> findAll() {
		return leaveService.findAll();
	}
}
