package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleMenu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.RoleMenuMapper;
import com.example.demo.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public void assignMenusToRole(Long roleId, Long[] menuIds) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 先删除角色原有的菜单
        roleMenuMapper.deleteByRoleId(roleId);

        // 检查菜单是否都存在
        for (Long menuId : menuIds) {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                throw new RuntimeException("菜单ID " + menuId + " 不存在");
            }
        }

        // 创建角色-菜单关联记录
        List<RoleMenu> roleMenus = Arrays.stream(menuIds)
                .map(menuId -> {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId(roleId);
                    roleMenu.setMenuId(menuId);
                    return roleMenu;
                })
                .collect(Collectors.toList());

        // 批量插入关联记录
        roleMenuMapper.insertBatch(roleMenus);
    }

    @Override
    @Transactional
    public void removeMenusFromRole(Long roleId, Long[] menuIds) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 删除指定的角色-菜单关联
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId)
                .in("menu_id", Arrays.asList(menuIds));
        roleMenuMapper.delete(wrapper);
    }

    @Override
    public List<Menu> getRoleMenus(Long roleId) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 获取角色的所有菜单ID
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(wrapper);

        // 如果没有关联的菜单，返回空列表
        if (roleMenus.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取所有关联的菜单详情
        List<Long> menuIds = roleMenus.stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());

        return menuMapper.selectBatchIds(menuIds);
    }

    @Override
    public List<Long> getRoleMenuIds(Long roleId) {
        return roleMenuMapper.selectMenuIdsByRoleId(roleId);
    }

    @Override
    public List<Role> getMenuRoles(Long menuId) {
        // 检查菜单是否存在
        Menu menu = menuMapper.selectById(menuId);
        if (menu == null) {
            throw new RuntimeException("菜单不存在");
        }

        // 获取菜单的所有角色ID
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_id", menuId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(wrapper);

        // 如果没有关联的角色，返回空列表
        if (roleMenus.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取所有关联的角色详情
        List<Long> roleIds = roleMenus.stream()
                .map(RoleMenu::getRoleId)
                .collect(Collectors.toList());

        return roleMapper.selectBatchIds(roleIds);
    }
}
