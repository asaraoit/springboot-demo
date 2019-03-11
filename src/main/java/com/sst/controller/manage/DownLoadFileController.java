package com.sst.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.DownloadFile;
import com.sst.model.FileCategory;
import com.sst.service.DownloadFileService;
import com.sst.service.FileCategoryService;

@RestController
@RequestMapping("/manage/downLoad")
public class DownLoadFileController {

	@Autowired
	DownloadFileService downloadFileService;
	
	@Autowired
	FileCategoryService fileCategoryService;
	
	
	/**
	 * 查看下载中心左侧菜单
	 * @return
	 */
	@RequestMapping("/getFileCategory")
	@ResponseBody
	public ReqResult getFileCategory(){
		List<FileCategory> list = fileCategoryService.getFileCategory();
		return ReqResult.COMMON_SUCCESS.setData(list);
	}
	
	/**
	 * 添加下载中心左侧菜单
	 * @param name
	 * @return
	 */
	@RequestMapping("/addOrUpdateFileCategory")
	@ResponseBody
	public ReqResult addOrUpdateFileCategory(@ModelAttribute FileCategory fileCategory){
		int i = 0;
		Long id = fileCategory.getId();
		if(id == null){
			fileCategory.setStatus(false);
			i = fileCategoryService.addFileCategory(fileCategory);
		}else{
			i = fileCategoryService.updateFileCategory(fileCategory);
		}
		if(i >0){
			return ReqResult.COMMON_SUCCESS.setMessage("保存成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("保存失败");
		}
	}
	
	/**
	 * 修改下载中心左侧菜单
	 * @param name
	 * @return
	 */
	/*@RequestMapping("/updateFileCategory")
	@ResponseBody
	public ReqResult updateFileCategory(@RequestParam("name") String name,@RequestParam("id") Long id, Long status){
		FileCategory fileCategory = new FileCategory();
		fileCategory.setId(id);
		fileCategory.setName(name);
		if(status != null && status == 0){
			fileCategory.setStatus(false);
		}else if(status!= null && status == 1){
			fileCategory.setStatus(true);
		}
		int i = fileCategoryService.updateFileCategory(fileCategory);
		if(i >0){
			return ReqResult.COMMON_SUCCESS.setMessage("修改成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("修改失败");
		}
	}*/
	
	
	/**
	 * 删除下载中心左侧菜单
	 * @param name
	 * @return
	 */
	@RequestMapping("/deleteFileCategory")
	@ResponseBody
	public ReqResult deleteFileCategory(@RequestParam("id") Long id){
		int i = fileCategoryService.deleteFileCategory(id);
		if(i >0){
			return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("删除失败");
		}
	}
	
	
	/**
	 * 查看下载中心版本
	 * @param categoryFile
	 * @return
	 */
	@RequestMapping("/getDownloadList")
	@ResponseBody
	public ReqResult getDownloadList(@RequestParam("categoryFile") Long categoryFile){
		
		List<DownloadFile> list = downloadFileService.getDownloadList(categoryFile);
		return ReqResult.COMMON_SUCCESS.setData(list);
		
	}
	
	/**
	 * 后台管理   查看下载中心版本--分页
	 * @param categoryFile
	 * @return
	 */
	@RequestMapping("/getDownloadPage")
	@ResponseBody
	public ReqResult getDownloadPage(Long categoryFile, int currentPage, int pageSize){
		
		IPage<DownloadFile> page = downloadFileService.getDownloadPage(categoryFile,currentPage,pageSize);
		return ReqResult.COMMON_SUCCESS.setData(page);
		
	}
	
	/**
	 * 删除下载中心版本
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDownload")
	@ResponseBody
	public ReqResult deleteDownload(@RequestParam("id") Long id){
		
		int i = downloadFileService.deleteDownload(id);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("删除失败");
		}
		
	}
	
	/**
	 * 下载中心  后台上传
	 * @param file
	 * @param id
	 * @param version
	 * @return
	 */
	@RequestMapping("/upload")
    @ResponseBody
    public ReqResult upload(@RequestParam(value = "downloadFile", required = false) MultipartFile file,Long id, String version, Long categoryFile, HttpServletRequest request){
        try {
        	int i = downloadFileService.upload(request,file, id, version, categoryFile);
        	if(i>0){
        		return ReqResult.COMMON_SUCCESS.setMessage("上传成功");
    		}else{
    			return ReqResult.COMMON_ERROR.setMessage("上传失败");
        	}
            
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("系统异常");
        }
    }
	
	/**
	 * 下载中心   页面下载
	 * @param response
	 * @param filenameServer
	 * @param id
	 * @return
	 */
	@RequestMapping("/download")
    @ResponseBody
    public void upload(HttpServletResponse response, String filenameServer, Long id){
        try {
        	downloadFileService.download(response, filenameServer, id);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 下载中心版本    禁用/启用
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateDownloadStatus")
	@ResponseBody
	public ReqResult updateDownloadStatus(@ModelAttribute DownloadFile downloadFile){
		int i = downloadFileService.updateDownloadStatus(downloadFile);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("设置失败");
		}
		
	}
	
	/**
	 * 下载中心   左侧菜单    禁用/启用
	 * @param fileCategory
	 * @return
	 */
	@RequestMapping("/updateCategoryStatus")
	@ResponseBody
	public ReqResult updateCategoryStatus(@ModelAttribute FileCategory fileCategory){
		int i = fileCategoryService.updateCategoryStatus(fileCategory);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("设置失败");
		}
		
	}
	
}
