package com.sst.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.SlideshowImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SlideshowImageService extends IService<SlideshowImage> {

    List<SlideshowImage> getEnabled();

    void saveOrUpdate(MultipartFile file,SlideshowImage slideshowImage) throws Exception;

    void deleteById(Long id);

    IPage<SlideshowImage> getAll(Long pageNumber, Long pageSize);

    void updateStatus(SlideshowImage slideshowImage);
}
