package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.dto.UserRoleCreate;
import com.example.demo.entity.UserRole;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    UserRoleMapper UserRoleMapper;
    @PostMapping("/create")
    public Result<?> create(@RequestBody @Valid UserRoleCreate userRole) {
        Long[] roleIds = userRole.getRoleIds();

        List<UserRole> newRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole newRole = new UserRole();
            newRole.setUserId(userRole.getUserId());
            newRole.setRoleId(roleId);
            newRoles.add(newRole);
        }
        UserRoleMapper.batchInsert(newRoles);
        return Result.success(true);
    }

    @PostMapping("/{id}")
    public Result<?> delete() {
        return Result.success(true);
    }

    @PostMapping("/update")
    public Result<?> update() {
        return Result.success(true);
    }

    @PostMapping("/list")
    public Result<?> query() {
        return Result.success(true);
    }
}
