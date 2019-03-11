package com.sst.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sst.common.response.ReqResult;
import com.sst.model.SlideshowImage;
import com.sst.service.SlideshowImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName SlideshowImageController
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/8 17:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/manage/slideshow")
public class SlideshowImageController  {

    @Autowired
    private SlideshowImageService slideshowImageService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ReqResult saveOrUpdate(@RequestParam(value="image",required=false) MultipartFile file, SlideshowImage slideshowImage){
        try {
            slideshowImageService.saveOrUpdate(file,slideshowImage);
            return ReqResult.COMMON_SUCCESS.setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("保存失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReqResult deleteById(Long id){
        try{
            slideshowImageService.deleteById(id);
            return ReqResult.COMMON_SUCCESS.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ReqResult.COMMON_ERROR.setMessage("删除失败");
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public ReqResult getAll( Long pageNumber,Long pageSize){
        IPage<SlideshowImage> slideshowImageIPage = slideshowImageService.getAll(pageNumber, pageSize);
        return ReqResult.COMMON_SUCCESS.setData(slideshowImageIPage);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ReqResult updateStatus(SlideshowImage slideshowImage){
        slideshowImageService.updateStatus(slideshowImage);
        return ReqResult.COMMON_SUCCESS.setMessage("设置成功");
    }
}
