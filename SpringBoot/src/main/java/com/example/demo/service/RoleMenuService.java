package com.example.demo.service;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;

import java.util.List;

public interface RoleMenuService {
    /**
     * 为角色分配菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     */
    void assignMenusToRole(Long roleId, Long[] menuIds);

    /**
     * 移除角色的菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     */
    void removeMenusFromRole(Long roleId, Long[] menuIds);

    /**
     * 获取角色的菜单列表
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<Menu> getRoleMenus(Long roleId);

    /**
     * 获取角色的菜单ID列表
     * @param roleId 角色ID
     * @return 菜单ID列表
     */
    List<Long> getRoleMenuIds(Long roleId);

    /**
     * 获取具有特定菜单权限的角色列表
     * @param menuId 菜单ID
     * @return 角色列表
     */
    List<Role> getMenuRoles(Long menuId);
}
