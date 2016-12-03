package com.wtkj.rms.leave.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wtkj.common.controller.BaseController;
import com.wtkj.rms.leave.model.vo.LeaveInfoVO;

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
	
	/**
	 * 发起请假需求
	 * @return 请求结果
	 */
	@RequestMapping({ "/manager" })
	public String manager(HttpServletRequest request) {
		return "/basic/leave/request";
	}
	
	/**
	 * 发起请假需求
	 * @return 请求结果
	 */
	@RequestMapping({ "/add" })
	public Object addLeave(LeaveInfoVO info) {
		return null;
	}
}
