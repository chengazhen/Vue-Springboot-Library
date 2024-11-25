package com.example.demo.mapper;

import com.example.demo.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectMenuList(SysMenu menu);
    
    SysMenu selectMenuById(Long menuId);
    
    int insertMenu(SysMenu menu);
    
    int updateMenu(SysMenu menu);
    
    int deleteMenuById(Long menuId);
    
    List<SysMenu> selectMenusByUserId(Long userId);
} 