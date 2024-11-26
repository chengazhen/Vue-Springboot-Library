package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.service.impl.RoleMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-menu")
public class RoleMenuController {

    @Autowired
    private RoleMenuServiceImpl roleMenuService;

    /**
     * 为角色分配菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return 操作结果
     */
    @PostMapping("/assign/{roleId}")
    public Result<?> assignMenusToRole(@PathVariable Long roleId, @RequestBody Long[] menuIds) {
        roleMenuService.assignMenusToRole(roleId, menuIds);
        return Result.success();
    }

    /**
     * 移除角色的菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return 操作结果
     */
    @PostMapping("/remove/{roleId}")
    public Result<?> removeMenusFromRole(@PathVariable Long roleId, @RequestBody Long[] menuIds) {
        roleMenuService.removeMenusFromRole(roleId, menuIds);
        return Result.success();
    }

    /**
     * 获取角色的菜单列表
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @GetMapping("/role/{roleId}")
    public Result<List<Menu>> getRoleMenus(@PathVariable Long roleId) {
        List<Menu> menus = roleMenuService.getRoleMenus(roleId);
        return Result.success(menus);
    }

    /**
     * 获取具有特定菜单权限的角色列表
     * @param menuId 菜单ID
     * @return 角色列表
     */
    @GetMapping("/menu/{menuId}")
    public Result<List<Role>> getMenuRoles(@PathVariable Long menuId) {
        List<Role> roles = roleMenuService.getMenuRoles(menuId);
        return Result.success(roles);
    }
}