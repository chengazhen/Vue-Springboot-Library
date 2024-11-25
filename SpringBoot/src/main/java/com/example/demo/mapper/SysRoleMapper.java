package com.example.demo.mapper;

import com.example.demo.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> selectRoleList(SysRole role);
    
    SysRole selectRoleById(Long roleId);
    
    int insertRole(SysRole role);
    
    int updateRole(SysRole role);
    
    int deleteRoleById(Long roleId);
    
    List<SysRole> selectRolesByUserId(Long userId);
} 