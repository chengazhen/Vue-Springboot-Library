package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.Date;
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
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        Arrays.stream(roleIds).forEach(roleId -> {
            if (roleMapper.selectById(roleId) == null) {
                throw new RuntimeException("角色ID " + roleId + " 不存在");
            }
            if (!userRoleMapper.exists(userId, roleId)) {
                userRoleMapper.insert(userId, roleId);
            }
        });
    }

    @Override
    @Transactional
    public void removeRolesFromUser(Long userId, Long[] roleIds) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        Arrays.stream(roleIds).forEach(roleId -> 
            userRoleMapper.delete(userId, roleId)
        );
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<User> getRoleUsers(Long roleId) {
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }
        return userMapper.selectUsersByRoleId(roleId);
    }

    // 基础的角色管理方法实现...
    @Override
    public Page<Role> list(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(Role::getName, name);
        }
        return roleMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return roleMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public boolean update(Role role) {
        role.setUpdateTime(new Date());
        return roleMapper.updateById(role) > 0;
    }

    @Override
    @Transactional
    public boolean create(Role role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        return roleMapper.insert(role) > 0;
    }
} 