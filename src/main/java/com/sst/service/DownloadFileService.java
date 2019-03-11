package com.sst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.DownloadFile;
import com.sst.util.PageBean;

public interface DownloadFileService extends IService<DownloadFile> {

    DownloadFile getNewest();
    
    public List<DownloadFile> getDownloadList(Long categoryFile);
    
    public int deleteDownload(Long id);
    
    public int updateDownloadStatus(DownloadFile downloadFile);

    int upload(HttpServletRequest request, MultipartFile file, Long id, String version, Long categoryFile) throws Exception;
    
    int download(HttpServletResponse response,String filenameServer, Long id) throws Exception;
    
    IPage<DownloadFile> getDownloadPage(Long categoryFile, int currentPage, int pageSize);

    IPage<DownloadFile> getEnabled(Long categoryFile, int currentPage, int pageSize);
}
