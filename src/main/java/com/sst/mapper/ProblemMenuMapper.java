package com.sst.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sst.model.FunctionCard;
import com.sst.model.ProblemMenu;

@Mapper
public interface ProblemMenuMapper extends BaseMapper<ProblemMenu> {

	List<ProblemMenu> getMenu();
	
	int addMenu(ProblemMenu problemMenu);
	
	int updateMenu(ProblemMenu problemMenu);
}
