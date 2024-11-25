package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    void batchInsert(List<UserRole> newRoles);
    boolean exists(Long userId, Long roleId); // Add this line
    void delete(Long userId, Long roleId); // Add this line
    void insert(Long userId, Long roleId); // Add this line
    List<Role> selectRolesByUserId(Long userId); // Add this line
    List<User> selectUsersByRoleId(Long roleId); // Add this line
}
