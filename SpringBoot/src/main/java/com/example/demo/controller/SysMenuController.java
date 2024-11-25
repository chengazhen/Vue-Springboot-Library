package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.SysMenu;
import com.example.demo.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/menu")
public class SysMenuController {
    
    @Autowired
    @Qualifier("sysMenuServiceImpl")
    private SysMenuService menuService;
    
    @GetMapping("/list")
    public Result list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return Result.success(menus);
    }
    
    @GetMapping("/{menuId}")
    public Result getInfo(@PathVariable Long menuId) {
        return Result.success(menuService.selectMenuById(menuId));
    }
    
    @PostMapping
    public Result add(@RequestBody SysMenu menu) {
        return Result.success(menuService.insertMenu(menu));
    }
    
    @PutMapping
    public Result edit(@RequestBody SysMenu menu) {
        return Result.success(menuService.updateMenu(menu));
    }
    
    @DeleteMapping("/{menuId}")
    public Result remove(@PathVariable Long menuId) {
        return Result.success(menuService.deleteMenuById(menuId));
    }
} 