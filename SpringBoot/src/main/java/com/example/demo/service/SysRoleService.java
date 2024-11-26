package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.Menu;
import java.util.List;

public interface SysRoleService {
    /**
     * 为用户分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     */
    void assignRolesToUser(Long userId, Long[] roleIds);

    /**
     * 移除用户的角色
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     */
    void removeRolesFromUser(Long userId, Long[] roleIds);

    /**
     * 获取用户的所有角色
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> getUserRoles(Long userId);

    /**
     * 获取角色的所有用户
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<User> getRoleUsers(Long roleId);

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
     * 获取角色的所有菜单权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<Menu> getRoleMenus(Long roleId);

    /**
     * 获取菜单的所有授权角色
     * @param menuId 菜单ID
     * @return 角色列表
     */
    List<Role> getMenuRoles(Long menuId);

    /**
     * 分页查询角色列表
     */
    Page<Role> list(Integer pageNum, Integer pageSize, String name);

    /**
     * 删除角色
     */
    boolean delete(Long id);

    /**
     * 更新角色
     */
    boolean update(Role role);

    /**
     * 创建角色
     */
    boolean create(Role role);
} 
