package com.sst.controller.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.DownloadFile;
import com.sst.model.ProblemContent;
import com.sst.model.ProblemMenu;
import com.sst.service.ProblemContentService;
import com.sst.service.ProblemMenuService;
import com.sst.util.PageBean;

/**
 * @Auther: shk
 * @Date: 2018/11/9 18:15
 * @Description:
 */
@RestController
@RequestMapping("/manage/problem")
public class CommonProblemController {

	@Autowired
	ProblemMenuService problemMenuService;
	
	@Autowired
	ProblemContentService problemContentService;

	/**
	 * 查询所有菜单列表
	 * @return
	 */
	@RequestMapping("/selectMenu")
    @ResponseBody
    public ReqResult selectMenu(){
		List<ProblemMenu> list = problemMenuService.getMenu();
		return ReqResult.COMMON_SUCCESS.setData(list);
    }
	
	/**
	 * 添加/修改菜单列表
	 * @param name
	 * @return
	 */
	@RequestMapping("/addOrUpdateMenu")
    @ResponseBody
    public ReqResult addOrUpdateMenu(@ModelAttribute ProblemMenu problemMenu){
		int i = 0;
		Long id = problemMenu.getId();
		if(id == null){
			problemMenu.setStatus(false);
			i = problemMenuService.addMenu(problemMenu);
		}else{
			i = problemMenuService.updateMenu(problemMenu);
		}
		
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("保存成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("保存失败");
		}
		
    }
	
	/**
	 * 修改菜单列表
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateMenu")
    @ResponseBody
    public ReqResult updateMenu(@ModelAttribute ProblemMenu menu){
		int i = problemMenuService.updateMenu(menu);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("修改成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("修改失败");
		}
		
    }
	
	/**
	 * 删除菜单列表
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMenu")
    @ResponseBody
    public ReqResult deleteMenu(Long id){
		int i = problemMenuService.deleteMenu(id);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("删除失败");
		}
		
    }
	
	/**
	 * 查询所有内容
	 * @return
	 */
	@RequestMapping("/selectContent")
    @ResponseBody
    public ReqResult selectContent(){
		List<ProblemContent> list = problemContentService.getContent();
		return ReqResult.COMMON_SUCCESS.setData(list);
    }
	
	/**
	 * 后台管理 --菜单查询内容 分页
	 * @param pmId
	 * @return
	 */
	@RequestMapping("/getContentById")
    @ResponseBody
    public ReqResult getContentById(Long pmId, int currentPage,int pageSize){
		
		IPage<ProblemContent> page = problemContentService.getContentById(pmId, currentPage, pageSize );
		return ReqResult.COMMON_SUCCESS.setData(page);
    }
	
	/**
	 * 常见问题模糊查询
	 * @param title
	 * @return
	 */
	@RequestMapping("/getContentByTitle")
    @ResponseBody
    public ReqResult getContentByTitle(@ModelAttribute ProblemContent problemContent){
		
		List<ProblemContent> list = problemContentService.getContentByTitle(problemContent);
		return ReqResult.COMMON_SUCCESS.setData(list);
    }
	
	/**
	 * 添加/修改内容信息
	 * @param problemContent
	 * @return
	 */
	@RequestMapping("/addOrUpdateContent")
    @ResponseBody
    public ReqResult addOrUpdateContent(@ModelAttribute ProblemContent problemContent){
		int i = 0;
		Long id = problemContent.getId();
		if(id == null){
			problemContent.setStatus(false); //默认禁用
			i = problemContentService.addContent(problemContent);
		}else{
			i = problemContentService.updateContent(problemContent);
		}
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("保存成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("保存失败");
		}
    }
	
	/**
	 * 修改内容信息
	 * @param problemContent
	 * @return
	 */
	@RequestMapping("/updateContent")
    @ResponseBody
    public ReqResult updateContent(@ModelAttribute ProblemContent problemContent){
		
		int i = problemContentService.updateContent(problemContent);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("修改成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("修改失败");
		}
    }
	
	/**
	 * 删除内容信息
	 * @param problemContent
	 * @return
	 */
	@RequestMapping("/deleteContent")
    @ResponseBody
    public ReqResult deleteContent(Long id){
		
		int i = problemContentService.deleteContent(id);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("删除失败");
		}
    }
	
	/**
	 * 常见问题  内容    禁用/启用
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateContentStatus")
	@ResponseBody
	public ReqResult updateContentStatus(@ModelAttribute ProblemContent problemContent){

		int i = problemContentService.updateContentStatus(problemContent);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("设置失败");
		}
  	}
	
	/**
	 * 常见问题    左侧菜单   启用/禁用
	 * @param problemMenu
	 * @return
	 */
	@RequestMapping("/updateMenuStatus")
	@ResponseBody
	public ReqResult updateMenuStatus(@ModelAttribute ProblemMenu problemMenu){
		
		int i = problemMenuService.updateMenuStatus(problemMenu);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("设置失败");
		}
	}

}
