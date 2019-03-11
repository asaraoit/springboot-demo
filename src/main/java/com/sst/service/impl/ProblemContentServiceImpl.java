package com.sst.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.ProblemContentMapper;
import com.sst.model.ProblemContent;
import com.sst.model.ProblemMenu;
import com.sst.service.ProblemContentService;


@Service
public class ProblemContentServiceImpl extends ServiceImpl<ProblemContentMapper , ProblemContent>
        implements ProblemContentService {

	@Override
	public List<ProblemContent> getContent() {
		// TODO Auto-generated method stub
		return baseMapper.getContent();
	}
	
	/**
	 *后台管理 常见问题内容列表
	 */
	@Override
	public IPage<ProblemContent> getContentById(Long pmId, int currentPage, int pageSize) {
		Page<ProblemContent> page = new Page<ProblemContent>(currentPage, pageSize);
		QueryWrapper<ProblemContent> queryWrapper = Condition.create(new ProblemContent()).eq("pmId",pmId);
		IPage<ProblemContent> iPage = baseMapper.selectPage(page, queryWrapper);
		System.out.println(iPage);
		return iPage;
	}
	
	//查询全部常见问题列表
	@Override
	public IPage<ProblemContent> getContentList( int currentPage, int pageSize) {
		Page<ProblemContent> page = new Page<ProblemContent>(currentPage, pageSize);
		QueryWrapper<ProblemContent> queryWrapper = Condition.create(new ProblemContent()).eq("status", 1);
		IPage<ProblemContent> iPage = baseMapper.selectPage(page, queryWrapper);
		System.out.println(iPage);
		return iPage;
	}
	
	//搜索查询--客户端
	@Override
	public List<ProblemContent> getContentByTitle(ProblemContent problemContent) {
		QueryWrapper<ProblemContent> queryWrapper = null;
		if(problemContent.getPmId() != null){
			queryWrapper = Condition.create(new ProblemContent()).eq("status", 1).eq("pmId",problemContent.getPmId()).like("title", problemContent.getTitle());
		}else{
			queryWrapper = Condition.create(new ProblemContent()).eq("status", 1).like("title", problemContent.getTitle());
		}
		List<ProblemContent> list = baseMapper.selectList(queryWrapper);
		return list;
	}
    
	@Override
	public int addContent(ProblemContent ProblemContent) {
		int i = baseMapper.addContent(ProblemContent);
		return i;
	}
	
	@Override
	public int updateContent(ProblemContent ProblemContent) {
		int i = baseMapper.updateByIdContent(ProblemContent);
		return i ;
	}
	
	@Override
	public int deleteContent(Long id) {
		int i = baseMapper.deleteById(id);
		return i ;
	}
	
	/**
	 * 常见问题  内容  禁用/启用
	 * @param problemContent
	 * @return
	 */
	@Override
	public int updateContentStatus(ProblemContent problemContent){
		return baseMapper.updateById(problemContent);
	}
	
	/**
	 * 客户端常见问题内容列表
	 */
	@Override
    public IPage<ProblemContent> getEnabled(Long pmId, int currentPage, int pageSize) {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("pmId", pmId);
		map.put("status", 1);
		Page<ProblemContent> page = new Page<ProblemContent>(currentPage, pageSize);
		QueryWrapper<ProblemContent> queryWrapper = Condition.create(new ProblemContent()).allEq(map);
		IPage<ProblemContent> iPage = baseMapper.selectPage(page, queryWrapper);
        
        return iPage;
    }
}
