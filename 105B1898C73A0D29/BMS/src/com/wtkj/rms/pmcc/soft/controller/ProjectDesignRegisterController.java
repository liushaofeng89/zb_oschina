package com.wtkj.rms.pmcc.soft.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtkj.common.GlobalConstant;
import com.wtkj.common.Grid;
import com.wtkj.common.Json;
import com.wtkj.common.PageFilter;
import com.wtkj.common.SessionInfo;
import com.wtkj.common.controller.BaseController;
import com.wtkj.common.model.User;
import com.wtkj.common.service.UserServiceI;
import com.wtkj.rms.pmcc.soft.model.ProjectDesignRegister;
import com.wtkj.rms.pmcc.soft.service.ProjectDesignRegisterServiceI;

/**
 * 工程设计登记
 */
@Controller
@RequestMapping("/projectDesignRegister")
public class ProjectDesignRegisterController extends BaseController {

	@Autowired
	private ProjectDesignRegisterServiceI projectDesignRegisterService;

	@Autowired
	private UserServiceI userService;

	/**
	 * 菜单列表页面
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("/manager")
	public String listPage(HttpServletRequest request) {
		return "/basic/projectDesignRegister/list";
	}
	/**
	 * 新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/basic/projectDesignRegister/add";
	}
	/**
	 * 编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);
		return "/basic/projectDesignRegister/edit";
	}
	/**
	 * 详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/detailPage")
	public String detailPage(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);
		return "/basic/projectDesignRegister/detail";
	}

	/**
	 * 列表查询
	 * @param vo
	 * @param ph
	 * @param request
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(ProjectDesignRegister vo, PageFilter ph, HttpServletRequest request) {
		Grid grid = new Grid();
		SessionInfo sessionInfo = getSessionInfo(request);

		if (sessionInfo.getId() != null && sessionInfo.getId() > 0) {
			User user = userService.get(sessionInfo.getId());
			// 普通用户看到自己的申请信息
			if (user.getRoleNames().indexOf("普通用户") >= 0) {
//				vo.setApplierId(sessionInfo.getId());
			}
		}
		grid.setRows(projectDesignRegisterService.dataGrid(vo, ph));
		grid.setTotal(projectDesignRegisterService.count(vo, ph));
		return grid;
	}
	/**
	 * 保存
	 * @param pdrr
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json save(ProjectDesignRegister pdrr, HttpServletRequest request) {
		Json j = new Json();
		try {
			if (pdrr != null) {
				// 工程所在地
				String projectAddress = pdrr.getProvice() + "" + pdrr.getCity() + "" + pdrr.getCounty();
				pdrr.setProjectAddress(projectAddress);
				// 添加时间
				pdrr.setCreatTime(new Date());
				SessionInfo sessionInfo = 
						(SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
				// 添加人ID
				if (sessionInfo.getId() > 0) {
					pdrr.setCreater(String.valueOf(sessionInfo.getId()));
				}
				// 添加人中文名
				pdrr.setCreaterName(sessionInfo.getName());
				// 删除标记
				pdrr.setDelFlag("0");
			}
			Long id = projectDesignRegisterService.save(pdrr, request);
			if (id > 0) {
				pdrr.setId(id);
			}
			j.setSuccess(true);
			j.setObj(pdrr);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	/**
	 * 编辑保存
	 * @param pdrr
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ProjectDesignRegister pdrr, HttpServletRequest request) {
		Json j = new Json();
		try {
			if (pdrr != null) {
				// 工程所在地
				String projectAddress = pdrr.getProvice() + "" + pdrr.getCity() + "" + pdrr.getCounty();
				pdrr.setProjectAddress(projectAddress);
				// 添加时间
				pdrr.setCreatTime(new Date());
				SessionInfo sessionInfo = 
						(SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
				// 添加人ID
				if (sessionInfo.getId() > 0) {
					pdrr.setCreater(String.valueOf(sessionInfo.getId()));
				}
				// 添加人中文名
				pdrr.setCreaterName(sessionInfo.getName());
				// 删除标记
				pdrr.setDelFlag("0");
			}
			projectDesignRegisterService.update(pdrr, request);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除
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
		try {
			projectDesignRegisterService.delete(ids);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		return j;
	}

	/**
	 * 通过id加载单个
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public ProjectDesignRegister get(Long id) {
		ProjectDesignRegister obj = projectDesignRegisterService.find(id);
//		if(obj != null){
//			String section = obj.getSection();
//			if(section != null && !"".equals(section)){
//				obj.setSection_show(section.split(","));
//			}
//		}
		return obj;
	}

}