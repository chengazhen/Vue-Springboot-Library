package com.example.demo.service;

import com.example.demo.entity.SysMenu;
import java.util.List;

public interface SysMenuService {
    List<SysMenu> selectMenuList(SysMenu menu);
    
    SysMenu selectMenuById(Long menuId);
    
    int insertMenu(SysMenu menu);
    
    int updateMenu(SysMenu menu);
    
    int deleteMenuById(Long menuId);
    
    List<SysMenu> selectMenusByUserId(Long userId);
} 