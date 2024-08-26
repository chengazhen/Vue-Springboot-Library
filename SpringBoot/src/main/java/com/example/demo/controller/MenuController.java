package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.commom.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuMapper MenuMapper;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Menu> wrappers = Wrappers.<Menu>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrappers.like(Menu::getName,search);
        }

        try {
            Page<Menu> BookPage = MenuMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
            return Result.success(BookPage);
        } catch (Exception e) {
            return Result.error("-1","查询失败");
        }
    }
}
