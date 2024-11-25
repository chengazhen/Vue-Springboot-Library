package com.example.demo.service;

import com.example.demo.entity.SysRole;
import java.util.List;

public interface SysRoleService {
    List<SysRole> selectRoleList(SysRole role);
    
    SysRole selectRoleById(Long roleId);
    
    int insertRole(SysRole role);
    
    int updateRole(SysRole role);
    
    int deleteRoleById(Long roleId);
    
    List<SysRole> selectRolesByUserId(Long userId);
} 