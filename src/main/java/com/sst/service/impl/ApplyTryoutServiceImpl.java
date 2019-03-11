package com.sst.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.ApplyTryoutMapper;
import com.sst.model.ApplyTryout;
import com.sst.service.ApplyTryoutService;

@Service
public class ApplyTryoutServiceImpl extends ServiceImpl<ApplyTryoutMapper,ApplyTryout>
		implements ApplyTryoutService {

	@Override
	public int addApplyTryout(ApplyTryout applyTryout){
		return baseMapper.addApplyTryout(applyTryout);
	}
	
	@Override
    public IPage<ApplyTryout> getAll(int pageNumber, int pageSize) {
        IPage<ApplyTryout> page = new Page<ApplyTryout>(pageNumber,pageSize);
        IPage<ApplyTryout> iPage = baseMapper.selectPage(page, null);
        return iPage;
		
    }
	
	@Override
    public int deleteById(Long id) {
		int i = baseMapper.deleteById(id);
        return i;
    }
	
	@Override
    public boolean updateById(ApplyTryout applyTryout) {
		boolean b = saveOrUpdate(applyTryout);
		return b;
        
    }

}
