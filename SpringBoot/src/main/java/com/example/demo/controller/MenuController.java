package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.MenuCreate;
import com.example.demo.dto.MenuUpdate;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.commom.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.MenuTree;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuMapper MenuMapper;

    @GetMapping("/list")
    public Result<?> list(){
        LambdaQueryWrapper<Menu> wrapper = Wrappers.<Menu>lambdaQuery();
        wrapper.orderByAsc(Menu::getSort);
        List<Menu> list = MenuMapper.selectList(wrapper);
        
        // 构建树形结构
        List<MenuTree> menuTree = new ArrayList<>();
        
        // 找出顶级菜单
        for (Menu menu : list) {
            if (menu.getPid() == null || menu.getPid() == 0) {
                MenuTree tree = convertToTree(menu, list);
                menuTree.add(tree);
            }
        }
        
        return Result.success(menuTree);
    }
    
    private MenuTree convertToTree(Menu menu, List<Menu> allMenus) {
        MenuTree tree = new MenuTree();
        tree.setId(menu.getId());
        tree.setName(menu.getName());
        tree.setPath(menu.getPath());
        tree.setComponent(menu.getComponent());
        tree.setIcon(menu.getIcon());
        tree.setPid(menu.getPid());
        tree.setSort(menu.getSort());
        
        List<MenuTree> children = new ArrayList<>();
        for (Menu m : allMenus) {
            if (menu.getId().equals(m.getPid())) {
                children.add(convertToTree(m, allMenus));
            }
        }
        
        if (!children.isEmpty()) {
            tree.setChildren(children);
        }
        
        return tree;
    }

    @PostMapping("/create")
    public Result<?> create(@RequestBody @Valid MenuCreate menu){
        Menu newMenu = new Menu();
        newMenu.setName(menu.getName());
        newMenu.setPath(menu.getPath());
        newMenu.setComponent(menu.getComponent());
        newMenu.setIcon(menu.getIcon());
        newMenu.setPid(menu.getPid());
        newMenu.setSort(menu.getSort());
        MenuMapper.insert(newMenu);
        return Result.success(true);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id){
        MenuMapper.deleteById(id);
        return Result.success(true);
    }


    @PostMapping("/update")
    public Result<?> update(@RequestBody @Valid MenuUpdate menu){
        // 判断是否存在
        Menu existMenu = MenuMapper.selectById(menu.getId());
        if (existMenu == null) {
            return Result.error("-1", "菜单不存在");
        }
        Menu newMenu = new Menu();
        newMenu.setId(menu.getId());
        newMenu.setName(menu.getName());
        newMenu.setPath(menu.getPath());
        newMenu.setComponent(menu.getComponent());
        newMenu.setIcon(menu.getIcon());
        newMenu.setSort(menu.getSort());
        MenuMapper.updateById(newMenu);
        return Result.success(true);
    }


}
