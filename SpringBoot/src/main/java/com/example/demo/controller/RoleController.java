package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.dto.RoleCreate;
import com.example.demo.dto.RoleUpdate;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleMapper RoleMapper;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "") String name) {
        LambdaQueryWrapper<Role> wrappers = Wrappers.<Role>lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            wrappers.like(Role::getName, name);
        }
        Page<Role> rolePage = RoleMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(rolePage);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        RoleMapper.deleteById(id);
        return Result.success(true);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody RoleUpdate role) {
        try {
            Role newRole = new Role();
            newRole.setId(role.getId());
            Date date = new Date();
            newRole.setUpdateTime(date);

            // Use Optional to simplify the null checks
            Optional.ofNullable(role.getName()).ifPresent(newRole::setName);
            Optional.ofNullable(role.getDescription()).ifPresent(newRole::setDescription);

            RoleMapper.updateById(newRole);
            return Result.success(true);
        } catch (Exception e) {
            return Result.error("500", e.getMessage());
        }
    }

    @PostMapping("/create")
    public Result<?> create(@RequestBody @Valid RoleCreate role) {
        try {
            Role newRole = new Role();
            newRole.setName(role.getName());
            newRole.setDescription(role.getDescription());
            RoleMapper.insert(newRole);
            return Result.success(true);
        } catch (Exception e) {
            return Result.error("500", e.getMessage());
        }
    }
}
