package com.sst.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.FileCategoryMapper;
import com.sst.model.FileCategory;
import com.sst.service.FileCategoryService;


@Service
public class FileCategoryServiceImpl extends ServiceImpl<FileCategoryMapper,FileCategory>
        implements FileCategoryService {
    
	@Override
	public List<FileCategory> getFileCategory() {
		// TODO Auto-generated method stub
		return baseMapper.getFileCategory();
	}
	
	
	@Override
	public int addFileCategory(FileCategory fileCategory) {
		// TODO Auto-generated method stub
		return baseMapper.insert(fileCategory);
	}


	@Override
	public int updateFileCategory(FileCategory fileCategory) {
		// TODO Auto-generated method stub
		return baseMapper.updateById(fileCategory);
	}


	@Override
	public int deleteFileCategory(Long id) {
		return baseMapper.deleteById(id);
	}
	
	/**
	 * 客户端下载中心左侧菜单
	 */
	@Override
    public List<FileCategory> getEnabled() {
        QueryWrapper<FileCategory> queryWrapper = Condition.create(new FileCategory()).eq("status",1);//状态为1-启用
        List<FileCategory> functionCards = baseMapper.selectList(queryWrapper);
        return functionCards;
    }


	/**
	 * 下载中心   左侧菜单   启用/禁用
	 */
	@Override
	public int updateCategoryStatus(FileCategory fileCategory) {
		int i = baseMapper.updateById(fileCategory);
		return i;
	}
}
