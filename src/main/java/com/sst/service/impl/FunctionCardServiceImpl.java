package com.sst.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.FunctionCardMapper;
import com.sst.model.FunctionCard;
import com.sst.service.FunctionCardService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName FunctionCardServiceImpl
 * @Description 功能卡服务层
 * @Author Asarao
 * @Date 2018/11/9 9:51
 * @Version 1.0
 */
@Service
public class FunctionCardServiceImpl extends ServiceImpl<FunctionCardMapper,FunctionCard>
        implements FunctionCardService {

    @Override
    public List<FunctionCard> getEnabled() {
        QueryWrapper<FunctionCard> queryWrapper = Condition.create(new FunctionCard()).eq("status",1);//状态为1-启用
        List<FunctionCard> functionCards = baseMapper.selectList(queryWrapper);
        return functionCards;
    }

    @Override
    public void saveOrUpdate(MultipartFile file, FunctionCard functionCard) throws IOException {
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
            String savePath = upload.getAbsolutePath() + File.separator + "card_image" ;
            String filePath = savePath + File.separator + filename;
            File dest = new File(filePath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);// 把file文件写入dest
            functionCard.setIcon(filename);
        }
        saveOrUpdate(functionCard);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<FunctionCard> getAll(Long pageNumber, Long pageSize) {
        IPage<FunctionCard> page = new Page<FunctionCard>(pageNumber,pageSize);
        IPage<FunctionCard> functionCardIPage = baseMapper.selectPage(page,null);
        return functionCardIPage;
    }

    @Override
    public void updateStatus(FunctionCard functionCard) {
        baseMapper.updateById(functionCard);
    }
}
