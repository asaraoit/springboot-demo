package com.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.ProblemContent;

@Mapper
public interface ProblemContentMapper extends BaseMapper<ProblemContent> {

	List<ProblemContent> getContent();
	
	List<ProblemContent> getContentById(Long pmId);
	
	public int getContentByIdCount(Long pmId);
	
	List<ProblemContent> getContentByTitle(ProblemContent problemContent);
	
	int addContent(ProblemContent problemContent);
	
	int updateByIdContent(ProblemContent problemContent);
}
