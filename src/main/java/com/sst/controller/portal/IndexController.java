package com.sst.controller.portal;

import com.sst.common.response.ReqResult;
import com.sst.service.DownloadFileService;
import com.sst.service.FunctionCardService;
import com.sst.service.SlideshowImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/8 14:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/portal/index")
public class IndexController {

    @Autowired
    private SlideshowImageService slideshowImageService;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    private FunctionCardService functionCardService;

    /*
     * @Author Asarao
     * @Description 获取轮播图图片
     * @Date 2018/11/8
     * @Param []
     * @return java.util.List<com.sst.model.SlideshowImage>
     **/
    @RequestMapping("/slideshow_image")
    @ResponseBody
    public ReqResult getSlideshowImages(){
        return ReqResult.COMMON_SUCCESS.setData(slideshowImageService.getEnabled());
    }

    /*
     * @Author Asarao
     * @Description 获取税神通最新版本
     * @Date 2018/11/9
     * @Param []
     * @return com.sst.model.DownloadFile
     **/
    @RequestMapping("/newest")
    @ResponseBody
    public ReqResult getNewest(){
        return ReqResult.COMMON_SUCCESS.setData(downloadFileService.getNewest());
    }

    /**
     * @Author Asarao
     * @Description 获取功能卡
     * @Date 2018/11/12
     * @Param []
     * @return com.sst.common.response.ReqResult
     **/
    @RequestMapping("/function_card")
    @ResponseBody
    public ReqResult getEnabledFunctionCard(){
        return ReqResult.COMMON_SUCCESS.setData(functionCardService.getEnabled());
    }
}
