package com.sst.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.FunctionCard;
import com.sst.service.FunctionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FunctionCardController
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/12 16:08
 * @Version 1.0
 */
@RequestMapping("/manage/function_card")
@RestController
public class FunctionCardController {

    @Autowired
    private FunctionCardService functionCardService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ReqResult saveOrUpdate(@RequestParam(value="image",required=false) MultipartFile file, FunctionCard functionCard){
        try {
            functionCardService.saveOrUpdate(file,functionCard);
            return ReqResult.COMMON_SUCCESS.setMessage("保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("保存失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReqResult deleteById(Long id){
        try{
            functionCardService.deleteById(id);
            return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("删除失败");
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public ReqResult getAll( Long pageNumber,Long pageSize){
        IPage<FunctionCard> functionCardIPage = functionCardService.getAll(pageNumber, pageSize);
        return ReqResult.COMMON_SUCCESS.setData(functionCardIPage);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ReqResult updateStatus(FunctionCard functionCard){
        try{
            functionCardService.updateStatus(functionCard);
            return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
        }catch (Exception e){
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("设置失败");
        }
    }
}
