package com.sst.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.ProblemContent;

public interface ProblemContentService extends IService<ProblemContent> {

    List<ProblemContent> getContent();
    
    public int addContent(ProblemContent problemContent);
	
    public int updateContent(ProblemContent problemContent);

    public int deleteContent(Long id);

    IPage<ProblemContent> getContentById(Long pmId, int currentPage, int pageSize);
    
    List<ProblemContent> getContentByTitle(ProblemContent problemContent);

    IPage<ProblemContent> getEnabled(Long pmId, int currentPage, int pageSize);

	int updateContentStatus(ProblemContent problemContent);

	IPage<ProblemContent> getContentList(int currentPage, int pageSize);
    
}
