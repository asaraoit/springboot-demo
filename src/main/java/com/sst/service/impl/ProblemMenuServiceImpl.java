package com.sst.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.mapper.ProblemMenuMapper;
import com.sst.model.FunctionCard;
import com.sst.model.ProblemMenu;
import com.sst.service.ProblemMenuService;


@Service
public class ProblemMenuServiceImpl extends ServiceImpl<ProblemMenuMapper,ProblemMenu>
        implements ProblemMenuService {

	@Override
	public List<ProblemMenu> getMenu() {
		// TODO Auto-generated method stub
		return baseMapper.getMenu();
	}
    
	@Override
	public int addMenu(ProblemMenu problemMenu) {
		// TODO Auto-generated method stub
		int i = baseMapper.addMenu(problemMenu);
		return i;
	}
	
	@Override
	public int updateMenu(ProblemMenu problemMenu) {
		// TODO Auto-generated method stub
		int i = baseMapper.updateMenu(problemMenu);
		return i ;
	}
	
	@Override
	public int deleteMenu(Long id) {
		int i = baseMapper.deleteById(id);
		return i ;
	}
	
	@Override
    public List<ProblemMenu> getEnabled() {
        QueryWrapper<ProblemMenu> queryWrapper = Condition.create(new ProblemMenu()).eq("status",1);//状态为1-启用
        List<ProblemMenu> functionCards = baseMapper.selectList(queryWrapper);
        return functionCards;
    }
	
	/**
	 * 常见问题  左侧菜单  启用/禁用
	 */
	@Override
	public int updateMenuStatus(ProblemMenu problemMenu) {
		// TODO Auto-generated method stub
		int i = baseMapper.updateById(problemMenu);
		return i ;
	}
}
