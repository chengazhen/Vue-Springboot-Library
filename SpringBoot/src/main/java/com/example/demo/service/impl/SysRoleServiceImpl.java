package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public void assignRolesToUser(Long userId, Long[] roleIds) {
        // 检查用户是否存在
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 检查角色是否都存在
        Arrays.stream(roleIds).forEach(roleId -> {
            if (roleMapper.selectById(roleId) == null) {
                throw new RuntimeException("角色ID " + roleId + " 不存在");
            }
        });

        // 为用户分配角色
        Arrays.stream(roleIds).forEach(roleId -> {
            if (!userRoleMapper.exists(userId, roleId)) {
                userRoleMapper.insert(userId, roleId);
            }
        });
    }

    @Override
    @Transactional
    public void removeRolesFromUser(Long userId, Long[] roleIds) {
        // 检查用户是否存在
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 移除指定的角色
        Arrays.stream(roleIds).forEach(roleId -> 
            userRoleMapper.delete(userId, roleId)
        );
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        // 检查用户是否存在
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        return userRoleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<User> getRoleUsers(Long roleId) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }
        
        return userRoleMapper.selectUsersByRoleId(roleId);
    }
} 