package com.example.demo.service.impl;

import com.example.demo.entity.SysRole;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public int insertRole(SysRole role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public int updateRole(SysRole role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
} 