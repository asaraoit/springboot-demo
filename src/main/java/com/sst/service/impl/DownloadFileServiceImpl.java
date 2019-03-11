package com.sst.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.SstIntroApplication;
import com.sst.mapper.DownloadFileMapper;
import com.sst.model.DownloadFile;
import com.sst.model.ProblemContent;
import com.sst.service.DownloadFileService;
import com.sst.util.PageBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DownloadFileServiceImpl
 * @Description TODO
 * @Author shk
 * @Date 2018/11/12 9:09
 * @Version 1.0
 */
@Service
@Slf4j
public class DownloadFileServiceImpl extends ServiceImpl<DownloadFileMapper,DownloadFile>
        implements DownloadFileService {
    @Override
    public DownloadFile getNewest() {
        return baseMapper.getNewest();
    }
    
    /**
     * 查询下载中心版本
     */
    public List<DownloadFile> getDownloadList(Long categoryFile){
    	List<DownloadFile> list = baseMapper.getDownloadList(categoryFile);
    	return list;
    }
    
    /**
     * 后台管理  查询下载中心版本--分页
     * @param categoryFile
     * @param currentPage
     * @param pageSize
     * @return
     */
    public IPage<DownloadFile> getDownloadPage(Long categoryFile, int currentPage, int pageSize){
    	Page<DownloadFile> page = new Page<DownloadFile>(currentPage,pageSize);
    	QueryWrapper<DownloadFile> wrapper = Condition.create(new DownloadFile()).eq("category_file", categoryFile);
    	IPage<DownloadFile> iPage = baseMapper.selectPage(page, wrapper);
    	return iPage;
    }
    
    /**
     * 删除下载中心版本
     */
    public int deleteDownload(Long id){
    	int i = baseMapper.deleteById(id);
    	return i;
    }

    /**
     * 添加 下载中心版本
     * @param downloadFile
     * @return
     */
    public int addDownload(DownloadFile downloadFile){
    	int i = baseMapper.addDownloadFile(downloadFile);
    	return i;
    }
    
    /**
     * 修改 下载中心版本
     * @param downloadFile
     * @return
     */
    public int updateDownload(DownloadFile downloadFile){
    	int i = baseMapper.updateDownloadFile(downloadFile);
    	return i;
    }
    
    /**
     * 启用/禁用下载中心版本
     * @param downloadFile
     * @return
     */
    public int updateDownloadStatus(DownloadFile downloadFile){
    	int i = baseMapper.updateById(downloadFile);
    	return i;
    }
    
    /**
     * 上传
     */
	@Override
	public int upload(HttpServletRequest request, MultipartFile file, Long id, String version, Long categoryFile) throws Exception {
		DownloadFile downloadFile = new DownloadFile();
		if(file != null){
			String filename = file.getOriginalFilename();
	        String ext = filename.substring(filename.indexOf("."));
	        String filenameServer = UUID.randomUUID() + ext;
	        
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
	        String savePath = upload.getAbsolutePath() + File.separator + "download_file" ;
	        String filePath = savePath + File.separator + filenameServer;
	        File dest = new File(filePath);
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
	        file.transferTo(dest);// 把file文件写入dest
	        Long len = file.getSize();
//	        Long len = dest.length();
	        String fileSize = this.FormetFileSize(len);
	        downloadFile.setFilenameServer(filenameServer);
	        downloadFile.setFilename(filename);
	        downloadFile.setFileSize(fileSize);
		}
        
        int i=0;
        if(id == null){
            downloadFile.setVersion(version);
            downloadFile.setCategoryFile(categoryFile);
            downloadFile.setStatus(false);
            i = this.addDownload(downloadFile);
        }else{
        	downloadFile = baseMapper.selectById(id);
            downloadFile.setVersion(version);
            downloadFile.setCategoryFile(categoryFile);
            i = this.updateDownload(downloadFile);
        }
        
        return i;
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param filenameServer
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public int download(HttpServletResponse response, String filenameServer, Long id) throws Exception{
		//获取根目录
        File path=new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()){
            path=new File("");
        }
        log.debug("服务器文件名称:"+filenameServer);
        //上传目录为/static/upload/,则可以如下获取
        String uploadPath = "static" + File.separator + "upload";
        File upload=new File(path.getAbsolutePath(),uploadPath);
        if(!upload.exists()) {
            upload.mkdirs();
        }
        //文件保存的路径
        String savePath = upload.getAbsolutePath() + File.separator + "download_file" ;
        String filePath = savePath + File.separator + filenameServer;
        File file = new File(filePath);
        log.debug("file路径:"+file);
        if(file.getParentFile().exists()){ //判断文件父目录是否存在
        	// 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filenameServer, "UTF-8"));
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //修改下载次数
        int i = baseMapper.updateDownloadCount(id);
        
        return i;
	}
	
	/**
     * 计算文件大小
     * 
     * @param file 文件length
     * @return 文件大小
     */
    public String FormetFileSize(Long fileLength) {
        String fileSizeString = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength != null) {
            if (fileLength < 1024) {
                fileSizeString = df.format((double) fileLength) + "B";
            }
            else if (fileLength < 1048576) {
                fileSizeString = df.format((double) fileLength / 1024) + "K";
            }
            else if (fileLength < 1073741824) {
                fileSizeString = df.format((double) fileLength / 1048576) + "M";
            }
            else {
                fileSizeString = df.format((double) fileLength / 1073741824) + "G";
            }
        }
        return fileSizeString;
    }
    
    @Override
    public IPage<DownloadFile> getEnabled(Long categoryFile, int currentPage, int pageSize) {
    	QueryWrapper<DownloadFile> queryWrapper = Condition.create(new DownloadFile()).eq("category_file",categoryFile).eq("status", 1);
    	Page<DownloadFile> page = new Page<DownloadFile>(currentPage, pageSize);
    	IPage<DownloadFile> iPage = baseMapper.selectPage(page, queryWrapper);
		
        return iPage;
    }
}
