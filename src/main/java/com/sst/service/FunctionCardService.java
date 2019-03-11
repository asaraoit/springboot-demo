package com.sst.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.FunctionCard;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @InterfaceName FunctionCardService
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/9 9:49
 * @Version 1.0
 */
public interface FunctionCardService extends IService<FunctionCard> {

    List<FunctionCard> getEnabled();

    void saveOrUpdate(MultipartFile file, FunctionCard functionCard) throws IOException;

    void deleteById(Long id);

    IPage<FunctionCard> getAll(Long pageNumber, Long pageSize);

    void updateStatus(FunctionCard functionCard);
}
