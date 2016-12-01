package com.wtkj.rms.pmcc.soft.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtkj.common.GlobalConstant;
import com.wtkj.common.Grid;
import com.wtkj.common.Json;
import com.wtkj.common.PageFilter;
import com.wtkj.common.ProcessStateConstant;
import com.wtkj.common.model.Tuser;
import com.wtkj.common.model.User;
import com.wtkj.common.service.UserServiceI;
import com.wtkj.common.utils.DateUtil;
import com.wtkj.rms.pmcc.soft.model.PayrollRegister;
import com.wtkj.rms.pmcc.soft.model.PayrollRegisterVo;
import com.wtkj.rms.pmcc.soft.service.PayrollRegisterServiceI;
import com.wtkj.rms.process.controller.BaseProcessController;
import com.wtkj.rms.process.model.Process;
import com.wtkj.rms.process.model.ProcessVo;
import com.wtkj.rms.process.service.ProcessHistoryServiceI;
import com.wtkj.rms.process.service.ProcessServiceI;

/**
 * 工资发放登记
 */
@Controller
@RequestMapping("/payrollRegister")
public class PayrollRegisterController extends BaseProcessController {

	@Autowired
	private PayrollRegisterServiceI payrollRegisterService;
	@Autowired
	private UserServiceI userService;
	@Autowired
	private ProcessServiceI processService;
	@Autowired
	private ProcessHistoryServiceI processHistoryService;

	/**
	 * 菜单列表页面
	 * 
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("/manager")
	public String listPage(HttpServletRequest request) {

		return "/basic/payrollRegister/payrollRegister";
	}

	/**
	 * 列表查询
	 * 
	 * @param vo
	 * @param ph
	 * @param request
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(PayrollRegister vo, PageFilter ph,
			HttpServletRequest request) {
		Grid grid = new Grid();
		Long userId = getSessionInfo(request).getId();
		User user = userService.get(userId);
		if (!StringUtils.isEmpty(user.getRoleNames())) {
			List<PayrollRegister> ps = payrollRegisterService.dataGrid(user, vo, ph);
			List<PayrollRegisterVo> pvos = new ArrayList<PayrollRegisterVo>();
			for (PayrollRegister payrollRegister : ps) {
				pvos.add(toVo(payrollRegister));
			}
			grid.setRows(pvos);
			grid.setTotal(payrollRegisterService.count(user, vo, ph));
			return grid;
		}
		grid.setRows(payrollRegisterService.dataGrid(null, vo, ph));
		grid.setTotal(payrollRegisterService.count(null, vo, ph));
		return grid;
	}

	/**
	 * CRUD以及处理的界面统一管理
	 * 
	 * @param request
	 * @param handlerType处理类型add
	 *            ,detail,edit,handler
	 * @return
	 */
	@RequestMapping("/viewPage")
	public String viewPage(HttpServletRequest request, String viewType, Long id) {
		// 获取当前用户
		viewType = StringUtils.isEmpty(viewType) ? "" : viewType;

		PayrollRegister payrollRegister = null;
		if (!StringUtils.isEmpty(id) && Long.valueOf(id) > 0) {
			// 编辑，详情，处理
			payrollRegister = payrollRegisterService.find(id);
		} else {
			// 添加
			payrollRegister = new PayrollRegister();
		}

		// 申请人和申请时间
		request.setAttribute("payrollRegister", toVo(payrollRegister));
		request.setAttribute("viewType", viewType);

		// 根据类型返回 缴纳或者退回页面
		return "/basic/payrollRegister/payrollRegisterView";
	}

	/**
	 * add edit apply(3个操作通过传一个操作符控制，做成一个方法即可)
	 * 
	 * @param crr
	 * @param request
	 * @return
	 */
	@RequestMapping({ "/save" })
	@ResponseBody
	public Json save(PayrollRegisterVo vo, String actionType,
			HttpServletRequest request) {
		Json j = new Json();
		User user = getLoginUser(request);
		PayrollRegister payrollRegister = null;
		String msg = "", detail = "";
		Long docId = 0l;
		int processState = 0;
		try {
			if (GlobalConstant.ACTION_EDIT.equals(actionType)) {
				msg = "修改成功!";
				// 编辑，注意需要关联process
				payrollRegisterService.update(toPo(vo), request);
			} else {
				String nextOperator = "";
				if (GlobalConstant.ACTION_ADD.equals(actionType)) {
					msg = "添加成功!";
					processState = ProcessStateConstant.STATE_INIT;
					detail = user.getName() + " 于 "
							+ DateUtil.convertDateToString(new Date())
							+ " 工资编制成功,下一步:提交工资单!";
				} else if (GlobalConstant.ACTION_COMMIT.equals(actionType)) {
					msg = "提交成功!";
					processState = ProcessStateConstant.STATE_APPLYED;
					nextOperator = getNextOperatorIds("role_zhb_manger");
					detail = user.getName() + " 于 "
							+ DateUtil.convertDateToString(new Date())
							+ " 工资编制提交成功,下一步:员工确认";
				}

				if (vo.getId() != null && vo.getId() > 0) {
					payrollRegisterService.update(toPo(vo), request);
					docId = vo.getId();
				} else {
					//第一次添加
					vo.setCreaterId(user.getId());
					vo.setCreatTime(new Date());
					//重复添加判断
					Map<String, String> map = new HashMap<String, String>();
					map.put("staffId", vo.getStaffId()+"");
					map.put("creatTime", DateUtil.convertDateToString(new Date(), DateUtil.YYYY_MM_DD));
					List<PayrollRegister> ps = payrollRegisterService.find(map);
					if(ps.size() > 0){
						j.setSuccess(true);
						j.setMsg("该员工这个月的工作已经存在，不能重复添加！");
						return j;
					}
					docId = payrollRegisterService.add(toPo(vo), request);
				}

				// 申请提交后不能修改
				payrollRegister = payrollRegisterService.find(docId);

				Long processId = vo.getProcess_vo() == null ? null : vo
						.getProcess_vo().getId();
				// 更新流程
				Process process = updateProcess(request, processId,
						nextOperator, GlobalConstant.PROCESS_NAME_PAY, docId,
						user, processState);

				// 更新资源-关联流程
				payrollRegister.setProcess(process);
				payrollRegisterService.update(payrollRegister, request);

				// 更新流程历史记录
				updateHistory(request, user, process, detail);
			}
			j.setSuccess(true);
			j.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		if (StringUtils.isEmpty(ids)) {
			j.setMsg("没有记录!");
			j.setSuccess(true);
			return j;
		}

		String[] arr = ids.split(",");
		StringBuilder deleteProcessIds = new StringBuilder();
		for (String id : arr) {
			PayrollRegister obj = payrollRegisterService.find(Long.valueOf(id));
			if (obj != null && obj.getProcess() != null) {
				Process p = processService.get(obj.getProcess().getId());
				deleteProcessIds.append(obj.getProcess().getId() + ",");
				if (p.getState() > 0) {
					j.setMsg("选择记录中存在记录已经提交的，不可以删除！");
					j.setSuccess(false);
					return j;
				}
			}
		}

		try {
			// 级联删除流程信息
			if (!StringUtils.isEmpty(deleteProcessIds.toString())) {
				String processIds = deleteProcessIds.toString().substring(0,
						deleteProcessIds.length() - 1);
				processService.delete(processIds);
				// 级联上次流程流程历史记录
				processHistoryService.deleteByProcessId(processIds);
			}
			// 业务表删除
			payrollRegisterService.delete(ids);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	// 审核通过方法
	// 流程审批相关,process docid,remark
	// 前台需要将process 的id 置入
	// 审批通过
	@RequestMapping("/complate")
	@ResponseBody
	public Json complate(ProcessVo vo, HttpServletRequest request) {
		Json j = new Json();
		if (vo == null || vo.getId() <= 0) {
			j.setMsg("未关联流程实例，流程启动失败");
			return j;
		}

		Process po = processService.get(vo.getId());
		po.setRemark(vo.getRemark());// 审批意见
		po.setOption(vo.getOption());
		vo = process2Vo(po);
		if (vo != null && vo.getDocId() != null && vo.getDocId() > 0) {
			if (vo.getOption() == 1) {
				return abort(vo, request);
			}
			if (payrollRegisterService.find(vo.getDocId()) != null) {
				User user = getLoginUser(request);
				if (user != null) {
					String roleNames = user.getRoleNames();

					if (po.getState() == 1) {
						// 所有用户对自己的工资确认
						po.setState(2);
						String op = this.getNextOperator("role_salary_manger");// 工资管理员
						po.setNextOperator(op);
						updateHistory(request, user, po, "员工：" + user.getName()
								+ "已经确认，下一步操作人：" + op);
					}

					if (roleNames.indexOf("工资管理员") >= 0) {
						po.setState(3);
						String op = this.getNextOperator("role_top_manger");// 总经理
						po.setNextOperator(op);
						// 增加流程操作历史记录
						updateHistory(request, user, po,
								"工资管理员：" + user.getName() + "已经确认，下一步操作人：" + op);

					} else if (roleNames.indexOf("总经理") >= 0) {
						po.setState(4);
						String op = this.getNextOperator("role_account");// 会计
						po.setNextOperator(op);
						// 增加流程操作历史记录
						updateHistory(request, user, po,
								"总经理：" + user.getName() + "已经确认，下一步操作人：" + op);

					} else if (roleNames.indexOf("会计") >= 0) {
						po.setState(5);
						String op = this.getNextOperator("role_cashier");// 出纳
						po.setNextOperator(op);
						// 增加流程操作历史记录
						updateHistory(request, user, po, "会计：" + user.getName()
								+ "已经确认，下一步操作人：" + op);

					} else if (roleNames.indexOf("出纳") >= 0) {
						po.setState(6);
						po.setEndDT(new Date());
						// 增加流程操作历史记录
						updateHistory(request, user, po, "出纳：" + user.getName()
								+ "已经确认，流程结束!");

					} else if (roleNames.indexOf("超级管理员") >= 0) {
						// 可以审批所有的单子,注：流程的状态递增的数列
						po.setState(vo.getState() + 1);
						// 增加流程操作历史记录
						if (isProcessFinished(po)) {
							po.setEndDT(new Date());
							updateHistory(request, user, po,
									"超级管理员：" + user.getName() + "已经确认,流程结束!");

						} else {
							updateHistory(request, user, po,
									"超级管理员：" + user.getName() + "已经确认!");
						}
					}

					try {
						po.setArriveDT(new Date());
						processService.edit(po, request);
						j.setSuccess(true);
						j.setMsg("审批成功！");
					} catch (Exception e) {
						e.printStackTrace();
						j.setSuccess(false);
						j.setMsg("审批失败！");
						j.setMsg(e.getMessage());
					}
				}
			}
		}
		return j;
	}

	// 审批不通过
	@RequestMapping("/abort")
	@ResponseBody
	public Json abort(ProcessVo vo, HttpServletRequest request) {
		Json j = new Json();
		User user = getLoginUser(request);
		if (vo == null || vo.getId() <= 0 || vo.getDocId() == null
				|| vo.getDocId() <= 0) {
			j.setMsg("流程为空!");
			return j;
		}
		Process po = processService.get(vo.getId());

		if (po == null) {
			j.setMsg("流程为空,请联系管理员!");
			return j;
		}

		if (user == null) {
			j.setMsg("办理人为空,请重新登录!");
			return j;
		}

		if (payrollRegisterService.find(vo.getDocId()) == null) {
			j.setMsg("资源为空,数据异常!");
			return j;
		}

		if (!StringUtils.isEmpty(vo.getRemark())) {
			po.setRemark(vo.getRemark());
		}
		
		Long applyUserId = po.getApplyUser().getId();
		String applyUserName = "";
		if (applyUserId > 0) {
			User u = userService.get(applyUserId);
			applyUserName = u == null ? "" : u.getName();
		}

		String roleNames = user.getRoleNames();

		if (po.getState() == 1) {
			// 所有用户对自己的工资确认
			po.setState(-2);
			updateHistory(request, user, po, "工资管理员：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);
		} else if (roleNames.indexOf("工资管理员") >= 0) {
			po.setState(-3);
			// 增加流程操作历史记录
			updateHistory(request, user, po, "工资管理员：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);

		} else if (roleNames.indexOf("总经理") >= 0) {
			po.setState(-4);
			// 增加流程操作历史记录
			updateHistory(request, user, po, "总经理：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);

		} else if (roleNames.indexOf("会计") >= 0) {
			po.setState(-5);
			// 增加流程操作历史记录
			updateHistory(request, user, po, "会计：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);

		} else if (roleNames.indexOf("出纳") >= 0) {
			po.setState(-6);
			// 增加流程操作历史记录
			updateHistory(request, user, po, "出纳：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);

		} else if (roleNames.indexOf("超级管理员") >= 0) {
			// 可以审批所有的单子,注：流程的状态递增的数列
			po.setState(-(vo.getState() + 1));
			updateHistory(request, user, po, "超级管理员：" + user.getName()
					+ "退回，下一步操作人：" + applyUserName);
		}

		try {
			po.setArriveDT(new Date());
			po.setNextOperator(po.getApplyUserId() + "");
			processService.edit(po, request);
			j.setSuccess(true);
			j.setMsg("审批成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}

		return j;
	}

	// 查看那流程图
	@RequestMapping("/processChart")
	public String processChart(HttpServletRequest request, Long id) {
		Process process = payrollRegisterService.find(id).getProcess();
		if (process != null && process.getId() != null && process.getId() > 0) {
			process = processService.get(process.getId());
			request.setAttribute("process", process2Vo(process));
		}

		return "/basic/payrollRegister/processChart";
	}

	/**
	 * 通过id加载单个
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public PayrollRegister get(Long id) {
		PayrollRegister obj = payrollRegisterService.find(id);
		return obj;
	}

	/**
	 * po vo 转换
	 * 
	 * @param po
	 * @return
	 */
	private PayrollRegisterVo toVo(PayrollRegister po) {
		PayrollRegisterVo vo = new PayrollRegisterVo();
		BeanUtils.copyProperties(po, vo);
		if (po.getProcess() != null && po.getProcess().getId() != null
				&& po.getProcess().getId() > 0) {
			Process process = processService.get(po.getProcess().getId());
			vo.setProcess_vo(process2Vo(process));
		}

		if (po.getCreater() != null && po.getCreater().getId() != null
				&& po.getCreater().getId() > 0) {
			User u = userService.get(po.getCreater().getId());
			vo.setCreaterId(u.getId());
			vo.setCreaterName(u.getName());
		}

		if (po.getStaff() != null && po.getStaff().getId() != null
				&& po.getStaff().getId() > 0) {
			User u = userService.get(po.getStaff().getId());
			vo.setStaffId(u.getId());
			vo.setStaffName(u.getName());
		}

		return vo;
	}

	private PayrollRegister toPo(PayrollRegisterVo vo) {
		PayrollRegister po = new PayrollRegister();
		BeanUtils.copyProperties(vo, po);
		if (vo.getProcess_vo() != null) {
			po.setProcess(process2Po(vo.getProcess_vo()));
		}

		if (vo.getCreaterId() != null && vo.getCreaterId() > 0) {
			Tuser u = new Tuser();
			u.setId(vo.getCreaterId());
			po.setCreater(u);
		}

		if (vo.getStaffId() != null && vo.getStaffId() > 0) {
			Tuser u = new Tuser();
			u.setId(vo.getStaffId());
			po.setStaff(u);
		}
		return po;
	}

	@Override
	protected boolean isProcessFinished(Process process) {
		if (process != null && process.getId() != null && process.getId() > 0) {
			return process.getState() == 6;
		} else {
			return false;
		}
	}

}