package com.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.FileCategory;

@Mapper
public interface FileCategoryMapper extends BaseMapper<FileCategory> {

	List<FileCategory> getFileCategory();
}
