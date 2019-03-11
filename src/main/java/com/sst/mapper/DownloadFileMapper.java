package com.sst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.DownloadFile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DownloadFileMapper extends BaseMapper<DownloadFile> {
    DownloadFile getNewest();
    
    List<DownloadFile> getDownloadList(Long categoryFile);
    
    int addDownloadFile(DownloadFile downloadFile);
    
    int updateDownloadFile(DownloadFile downloadFile);
    
    int updateDownloadCount(Long id);
    
    int getDownloadByIdCount(Long categoryFile);
}
