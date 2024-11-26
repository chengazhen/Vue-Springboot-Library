package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
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

    // 基础的角色管理方法
    Page<Role> list(Integer pageNum, Integer pageSize, String name);
    boolean delete(Long id);
    boolean update(Role role);
    boolean create(Role role);
} 
