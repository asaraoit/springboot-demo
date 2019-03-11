package com.sst.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.ApplyTryout;
import com.sst.util.PageBean;

public interface ApplyTryoutService extends IService<ApplyTryout> {

	int addApplyTryout(ApplyTryout applyTryout);

	int deleteById(Long id);

	boolean updateById(ApplyTryout applyTryout);

	IPage<ApplyTryout> getAll(int pageNumber, int pageSize);
	
}
