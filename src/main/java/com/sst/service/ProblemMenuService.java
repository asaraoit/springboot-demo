package com.sst.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.model.ProblemMenu;

public interface ProblemMenuService extends IService<ProblemMenu> {

    List<ProblemMenu> getMenu();
    
    public int addMenu(ProblemMenu problemMenu);
	
    public int updateMenu(ProblemMenu problemMenu);

    public int deleteMenu(Long id);

	List<ProblemMenu> getEnabled();

	//常见问题  左侧菜单  启用/禁用
	int updateMenuStatus(ProblemMenu problemMenu);
}
