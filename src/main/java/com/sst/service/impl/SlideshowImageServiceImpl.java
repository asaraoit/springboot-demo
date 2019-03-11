package com.sst.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.SlideshowImageMapper;
import com.sst.model.SlideshowImage;
import com.sst.service.SlideshowImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName SlideshowImageServiceImpl
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/8 14:19
 * @Version 1.0
 */
@Service
public class SlideshowImageServiceImpl extends ServiceImpl<SlideshowImageMapper, SlideshowImage>
        implements SlideshowImageService {

    @Override
    public List<SlideshowImage> getEnabled() {
        QueryWrapper<SlideshowImage> queryWrapper = Condition.create(new SlideshowImage()).eq("status",1);
        List<SlideshowImage> slideshowImages = baseMapper.selectList(queryWrapper);
        return slideshowImages;
    }

    @Override
    public void saveOrUpdate(MultipartFile file,SlideshowImage slideshowImage) throws Exception {
        if(file != null){
            String filename = file.getOriginalFilename();
            String ext = filename.substring(filename.indexOf("."));
            filename = UUID.randomUUID() + ext;
            //获取根目录
            File path=new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()){
                path=new File("");
            }
            //上传目录为/static/upload/,则可以如下获取
            String uploadPath = "static" + File.separator + "upload";
            File upload=new File(path.getAbsolutePath(),uploadPath);
            if(!upload.exists()) {
                upload.mkdirs();
            }
            //文件保存的路径
            String savePath = upload.getAbsolutePath() + File.separator + "slideshow_image" ;
            String filePath = savePath + File.separator + filename;
            File dest = new File(filePath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);// 把file文件写入dest

            //存储数据库
            slideshowImage.setImageName(filename);
        }
        saveOrUpdate(slideshowImage);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<SlideshowImage> getAll(Long pageNumber, Long pageSize) {
        IPage<SlideshowImage> page = new Page<SlideshowImage>(pageNumber,pageSize);
        IPage<SlideshowImage> slideshowImageIPage = baseMapper.selectPage(page, null);
        return slideshowImageIPage;

    }

    @Override
    public void updateStatus(SlideshowImage slideshowImage) {
        Boolean status = slideshowImage.getStatus();
        if(true == status){ //启用某图片，禁用同等级的启用图片
            Map<String,Object> conditionMap = new HashMap<>();
            conditionMap.put("status",1);
            conditionMap.put("level",slideshowImage.getLevel());
            QueryWrapper<SlideshowImage> queryWrapper = Condition.create(new SlideshowImage()).allEq(conditionMap);
            SlideshowImage slideshowImage_1 = baseMapper.selectOne(queryWrapper);//启用的图片
            if(slideshowImage_1 !=null){
                slideshowImage_1.setStatus(false);
                baseMapper.updateById(slideshowImage_1);
            }
        }
        baseMapper.updateById(slideshowImage);
    }
}
