package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.SysRole;
import com.example.demo.service.impl.SysRoleServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {
    
    @Resource
    private SysRoleServiceImpl roleService;
    
    @GetMapping("/list")
    public Result list(SysRole role) {
        List<SysRole> roles = roleService.selectRoleList(role);
        return Result.success(roles);
    }
    
    @GetMapping("/{roleId}")
    public Result getInfo(@PathVariable Long roleId) {
        return Result.success(roleService.selectRoleById(roleId));
    }
    
    @PostMapping
    public Result add(@RequestBody SysRole role) {
        return Result.success(roleService.insertRole(role));
    }
    
    @PutMapping
    public Result edit(@RequestBody SysRole role) {
        return Result.success(roleService.updateRole(role));
    }
    
    @DeleteMapping("/{roleId}")
    public Result remove(@PathVariable Long roleId) {
        return Result.success(roleService.deleteRoleById(roleId));
    }
} 