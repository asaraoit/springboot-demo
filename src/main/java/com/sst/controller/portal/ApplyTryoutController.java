package com.sst.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.ApplyTryout;
import com.sst.service.ApplyTryoutService;

@RequestMapping("/portal/applyTry")
@RestController
public class ApplyTryoutController {

	@Autowired
	private ApplyTryoutService applyTryoutService;
	
	
	@RequestMapping( value = "/getApplyTryoutIPage", method=RequestMethod.POST)
	@ResponseBody
	public ReqResult getApplyTryoutIPage (int pageNumber,int pageSize){
		
		IPage<ApplyTryout> page = applyTryoutService.getAll(pageNumber, pageSize);
		return ReqResult.COMMON_SUCCESS.setData(page);
	}
	
	@RequestMapping("/addApplyTryout")
	@ResponseBody
	public ReqResult AddApplyTryout(@ModelAttribute ApplyTryout applyTryout){
		int i = applyTryoutService.addApplyTryout(applyTryout);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("添加成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("添加失败");
		}
		
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ReqResult deleteById(Long id){
		int i = applyTryoutService.deleteById(id);
		if(i>0){
			return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("删除失败");
		}
	}
	
	@RequestMapping("/updateById")
	@ResponseBody
	public ReqResult updateById(ApplyTryout applyTryout){
		boolean b = applyTryoutService.updateById(applyTryout);
		if(b){
			return ReqResult.COMMON_SUCCESS.setMessage("修改成功");
		}else{
			return ReqResult.COMMON_ERROR.setMessage("修改失败");
		}
	}
	
}
