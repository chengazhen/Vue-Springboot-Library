package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private RoleMapper roleMapper;

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