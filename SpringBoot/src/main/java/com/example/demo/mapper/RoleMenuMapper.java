package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.RoleMenu;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    
    /**
     * 批量插入角色-菜单关联记录
     */
    void insertBatch(@Param("roleMenus") List<RoleMenu> roleMenus);
    
    /**
     * 根据角色ID删除关联记录
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
    
    /**
     * 根据菜单ID删除关联记录
     */
    void deleteByMenuId(@Param("menuId") Long menuId);
    
    /**
     * 查询角色的菜单ID列表
     */
    List<Long> selectMenuIdsByRoleId(@Param("roleId") Long roleId);
    
    /**
     * 查询菜单的角色ID列表
     */
    List<Long> selectRoleIdsByMenuId(@Param("menuId") Long menuId);
    
    List<Menu> selectMenusByRoleId(Long roleId);
    List<Role> selectRolesByMenuId(Long menuId);

    @Select("SELECT m.* FROM menu m " +
            "LEFT JOIN role_menu rm ON m.id = rm.menu_id " +
            "WHERE rm.role_id = #{roleId} " +
            "ORDER BY m.sort")
    List<Menu> getMenusByRoleId(@Param("roleId") Long roleId);

}
