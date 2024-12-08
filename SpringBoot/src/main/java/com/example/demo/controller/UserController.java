package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.dto.UserInfo;
import com.example.demo.utils.JwtUtil;

import io.jsonwebtoken.Claims;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.demo.dto.LoginDTO;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名已重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @CrossOrigin
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, loginDTO.getUsername())
                .eq(User::getPassword, loginDTO.getPassword()));
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());  // 添加用户ID到claims中
            String token = jwtUtil.generateToken(claims);
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            return Result.success(response);
        }
        return Result.error("-1", "用户名或密码错误");
    }

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("abc123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping("/password")
    public Result<?> update(@RequestParam Integer id,
            @RequestParam String password2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        User user = new User();
        user.setPassword(password2);
        userMapper.update(user, updateWrapper);
        return Result.success();
    }

    @PutMapping
    public Result<?> password(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrappers.like(User::getUsername, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(userPage);
    }

    @GetMapping("/usersearch")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search1,
            @RequestParam(defaultValue = "") String search2,
            @RequestParam(defaultValue = "") String search3,
            @RequestParam(defaultValue = "") String search4) {
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(User::getId, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(User::getNickName, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(User::getPhone, search3);
        }
        if (StringUtils.isNotBlank(search4)) {
            wrappers.like(User::getAddress, search4);
        }
        wrappers.like(User::getRole, 2);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(userPage);
    }

    // 获取用户信息
    @GetMapping("/info/{id}")
    public Result<?> getUserInfo(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error("-1", "用户不存在");
        }
        List<Role> roles = userRoleMapper.selectRolesByUserId(id);
        UserInfo userInfo = new UserInfo(user, roles);
        return Result.success(userInfo);
    }

    @GetMapping("/profile")
    public Result<?> getUserProfile(@RequestHeader("Authorization") String token) {
        try {
            // 去掉 "Bearer " 前缀
            token = token.replace("Bearer ", "");

            // 从token中解析claims
            Claims claims = jwtUtil.parseToken(token);

            // 获取用户信息
            Long userId = Long.parseLong(claims.get("userId").toString());
            User user = userMapper.selectById(userId);

            // 获取用户角色
            List<Role> roles = userRoleMapper.selectRolesByUserId(userId);

            // 构建返回数据
            Map<String, Object> profile = new HashMap<>();
            profile.put("user", user);
            profile.put("roles", roles);

            return Result.success(profile);
        } catch (Exception e) {
            return Result.error("-1", "无效的token");
        }
    }
}
