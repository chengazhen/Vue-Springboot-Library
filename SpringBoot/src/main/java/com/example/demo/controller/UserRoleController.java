package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.commom.Result;
import com.example.demo.dto.UserRoleCreate;
import com.example.demo.dto.UserRoleUpdate;
import com.example.demo.entity.UserRole;
import com.example.demo.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    UserRoleMapper userRoleMapper;

    @PostMapping("/create")
    public Result<?> create(@RequestBody @Valid UserRoleCreate userRole) {
        Long userId = userRole.getUserId();
        userRoleMapper.delete(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));

        Long[] roleIds = userRole.getRoleIds();
        List<UserRole> newRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole newRole = new UserRole();
            newRole.setUserId(userId);
            newRole.setRoleId(roleId);
            newRoles.add(newRole);
        }
        userRoleMapper.batchInsert(newRoles);
        return Result.success(true);
    }

    @PostMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userRoleMapper.deleteById(id);
        return Result.success(true);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody @Valid UserRoleUpdate userRole) {
        Long[] roleIds = userRole.getRoleIds();
        List<UserRole> newRoles = Arrays.stream(roleIds).map(roleId -> {
            UserRole newRole = new UserRole();
            newRole.setUserId(userRole.getUserId());
            newRole.setRoleId(roleId);
            return newRole;
        }).collect(Collectors.toList());

        userRoleMapper.batchInsert(newRoles);

        return Result.success(true);
    }

}
