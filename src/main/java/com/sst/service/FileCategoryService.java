package com.sst.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.FileCategory;

public interface FileCategoryService extends IService<FileCategory> {

	List<FileCategory> getFileCategory();

	int addFileCategory(FileCategory fileCategory);
	
	int updateFileCategory(FileCategory fileCategory);
	
	int deleteFileCategory(Long id);

	List<FileCategory> getEnabled();

	int updateCategoryStatus(FileCategory fileCategory);

    
}
