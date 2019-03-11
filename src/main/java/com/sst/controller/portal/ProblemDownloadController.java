package com.sst.controller.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.DownloadFile;
import com.sst.model.FileCategory;
import com.sst.model.ProblemContent;
import com.sst.model.ProblemMenu;
import com.sst.service.DownloadFileService;
import com.sst.service.FileCategoryService;
import com.sst.service.ProblemContentService;
import com.sst.service.ProblemMenuService;
import com.sst.util.PageBean;

@RequestMapping("/portal/problemDownload")
@RestController
public class ProblemDownloadController {

	@Autowired
	ProblemContentService problemContentService;
	
	@Autowired
	ProblemMenuService problemMenuService;
	
	@Autowired
	FileCategoryService fileCategoryService;
	
	@Autowired
	DownloadFileService downloadFileService;
	/**
	 * 客户端常见问题左侧菜单
	 * @return
	 */
	@RequestMapping("/selectMenu")
	@ResponseBody
	public ReqResult selectMenu(){
		
		List<ProblemMenu> list = problemMenuService.getEnabled();
		return ReqResult.COMMON_SUCCESS.setData(list);
		
	}
	
	/**
	 * 客户端常见问题内容列表
	 * @param id
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectContent")
	@ResponseBody
	public ReqResult selectContent(Long pmId, int currentPage, int pageSize){
		
		IPage<ProblemContent> iPage = problemContentService.getEnabled(pmId, currentPage, pageSize);
		return ReqResult.COMMON_SUCCESS.setData(iPage);
	}
	
	/**
	 * 客户端所有常见问题内容列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getContentList")
	@ResponseBody
	public ReqResult getContentList(int currentPage, int pageSize){
		
		IPage<ProblemContent> iPage = problemContentService.getContentList(currentPage, pageSize);
		return ReqResult.COMMON_SUCCESS.setData(iPage);
	}
	
	/**
	 * 客户端下载中心左侧菜单
	 * @return
	 */
	@RequestMapping("/selectCategory")
	@ResponseBody
	public ReqResult selectCategory(){
		
		List<FileCategory> list = fileCategoryService.getEnabled();
		return ReqResult.COMMON_SUCCESS.setData(list);
		
	}
	
	/**
	 * 客户端下载中心内容列表
	 * @param idselectdownloadFile
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectdownloadFile")
	@ResponseBody
	public ReqResult selectdownloadFile(Long categoryFile, int currentPage, int pageSize){
		
		IPage<DownloadFile> iPage = downloadFileService.getEnabled(categoryFile, currentPage, pageSize);
		return ReqResult.COMMON_SUCCESS.setData(iPage);
		
	}
	
}
