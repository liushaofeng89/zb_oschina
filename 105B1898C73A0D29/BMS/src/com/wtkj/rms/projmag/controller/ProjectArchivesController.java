package com.wtkj.rms.projmag.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wtkj.common.Grid;
import com.wtkj.common.Json;
import com.wtkj.common.PageFilter;
import com.wtkj.common.controller.BaseController;
import com.wtkj.rms.project.model.ProjectArchiesInfoModel;
import com.wtkj.rms.project.model.ProjectBid;
import com.wtkj.rms.project.service.ProjectArchiesInfoService;
import com.wtkj.rms.project.service.ProjectBidServiceI;
import com.wtkj.rms.projmag.model.PrjArchievesUploadModel;
import com.wtkj.rms.projmag.model.ProjectArchives;
import com.wtkj.rms.projmag.service.ProjectArchivesServiceI;

@Controller
@RequestMapping("/projectArchives")
public class ProjectArchivesController extends BaseController {

	@Autowired
	private ProjectArchivesServiceI projectArchivesService;
	@Autowired
	private ProjectArchiesInfoService prjArchiesInfoService;
	@Autowired
	private ProjectBidServiceI projectBidService;

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/basic/project/projectArchives";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(ProjectArchives projectArchives, PageFilter ph) {
		Grid grid = new Grid();
		List<ProjectArchives> regs = projectArchivesService.dataGrid(projectArchives, ph);
		/*
		 * // 根据子状态判断状态值 for (ProjectArchives reg : regs) { // 未申请的记录 BigInteger
		 * countNotApplied = projectAppropriateAccountService
		 * .countByRegIdAndState(reg.getId(), 0);
		 * 
		 * 
		 * // 出纳确认的数目 BigInteger countCn = projectAppropriateAccountService
		 * .countByRegIdAndState(reg.getId(), 3);
		 * 
		 * // 总数 BigInteger baseCount = projectAppropriateAccountService
		 * .countByRegId(reg.getId());
		 * 
		 * // 总数为0或者只有未申请的记录 if (baseCount.intValue() == 0 ||
		 * countNotApplied.intValue() == baseCount.intValue()) {
		 * reg.setState(-1);// 所有的记录都是未申请或者没有记录则状态为未申请 } else { if
		 * (countCn.intValue() == baseCount.intValue() -
		 * countNotApplied.intValue()) { // 所有申请的已经被出纳全部确认，代表已经拨付完成
		 * reg.setState(2);// 拨付完成 } else if (countCn.intValue() == 0) { //
		 * 都没有确认，未拨付 reg.setState(0); } else { reg.setState(1);// 部分拨付 } } }
		 */
		grid.setRows(regs);
		grid.setTotal(projectArchivesService.count(projectArchives, ph));
		return grid;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Json remove(String id) {
		Json j = new Json();
		if (null != id && !id.isEmpty()) {
			prjArchiesInfoService.remove(id);
		}
		j.setSuccess(true);
		j.setMsg("更新数据库成功！");
		return j;
	}

	@RequestMapping("/addPrjArchieves")
	public String addPrjArchieves(PrjArchievesUploadModel uploadModel, HttpServletRequest req) {

		String scanningFilePath = "";
		if (!uploadModel.getArchieveScanning().isEmpty()) {
			scanningFilePath = saveFile(req, uploadModel.getPrjId(), uploadModel.getArchieveScanning());
		}

		ProjectArchiesInfoModel projectArchiesInfoModel = new ProjectArchiesInfoModel(Integer.parseInt(uploadModel
				.getPrjId()), uploadModel.getArchieveType(), scanningFilePath, uploadModel.getArchieveOriginalNo(),
				uploadModel.getArchieveOriginalPath(), uploadModel.getArchieveCopyNo(),
				uploadModel.getArchieveCopyPath(), uploadModel.getNote());

		prjArchiesInfoService.saveByArchieves(projectArchiesInfoModel);
		return "/basic/project/projectArchives";
	}

	private String saveFile(HttpServletRequest request, String prjId, MultipartFile myfile) {
		try {
			String fileName = myfile.getOriginalFilename();
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/" + prjId + "/");
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, fileName));
			Logger.getLogger(this.getClass()).info("create file at:" + realPath + fileName);
			return request.getSession().getServletContext().getRealPath("/WEB-INF/upload/" + prjId + "/")
					+ File.separator + fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping("/get")
	@ResponseBody
	public ProjectArchives get(Long id) {
		return projectArchivesService.get(id);
	}

	/**
	 * 加载所有已经中标的项目数据
	 * 
	 * @return 所有中标项目数据
	 */
	@RequestMapping("/loadFilteredPrjs")
	@ResponseBody
	public List<ProjectBid> loadFilteredPrjs(String filter) {
		// 处理统计信息
		List<ProjectBid> filterList = new ArrayList<ProjectBid>();

		List<ProjectBid> find = projectBidService.find(null);
		for (ProjectBid projectBid : find) {
			if (projectBid.getProjectName().contains(filter.trim())) {
				filterList.add(projectBid);
			}
		}
		return filterList;
	}

	@RequestMapping("/selectedPrjs")
	@ResponseBody
	public Json selectedPrjs(String ids, HttpServletRequest request) {
		Json j = new Json();
		if (!"[]".equals(ids)) {
			String[] idList = getIds(ids);
			List<ProjectBid> findAll = projectBidService.findAll();
			for (String id : idList) {
				for (ProjectBid prjBid : findAll) {
					if (id.equals(String.valueOf(prjBid.getId()))) {
						projectArchivesService.add(new ProjectArchives(prjBid), request);
						continue;
					}
				}
			}
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

	@RequestMapping("/download")
	public void download(HttpServletResponse response, String path) throws IOException {
		try {
			String newPath = new String(path.getBytes("iso-8859-1"), "UTF-8");
			InputStream fis = new BufferedInputStream(new FileInputStream(newPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + resolveFileName(path) + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String resolveFileName(String path) {
		int beginIndex = path.lastIndexOf(File.separator);
		return path.substring(beginIndex);
	}

	@RequestMapping("/viewPage")
	public String viewPage(HttpServletRequest request, String viewType, Long id) {
		// 获取当前用户
		ProjectArchives vo = null;
		if (!StringUtils.isEmpty(id) && Long.valueOf(id) > 0) {
			// 编辑，详情
			vo = projectArchivesService.get(id);
		} else {
			// 添加
			vo = new ProjectArchives();
		}
		request.setAttribute("projectArchives", vo);
		request.setAttribute("viewType", viewType);
		return "/basic/project/projectArchivesView";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Json save(ProjectArchives vo, String viewType, HttpServletRequest request) {
		// 普通员工提交
		Json j = new Json();
		viewType = StringUtils.isEmpty(viewType) ? "" : viewType;
		try {
			// 添加
			if (vo.getId() == null || vo.getId() <= 0) {
				projectArchivesService.add(vo, request);
				j.setObj(vo);
			} else {// 编辑或者处理
				projectArchivesService.edit(vo, request);
			}

			if ("add".equals(viewType)) {
				j.setMsg("添加成功！");
			} else if ("edit".equals(viewType)) {
				j.setMsg("修改成功！");
			}
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

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
			// TODO 关联拨付情况表，注意级联删除
			projectArchivesService.delete(ids);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}