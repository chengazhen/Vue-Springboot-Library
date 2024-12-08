package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.service.impl.RoleMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.MenuTree;
import com.example.demo.entity.MenuMeta;
import java.util.ArrayList;
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
    public Result<?> getRoleMenus(@PathVariable Long roleId) {
        // 获取角色的所有菜单
        List<Menu> menuList = roleMenuService.getRoleMenus(roleId);
        
        // 构建树形结构
        List<MenuTree> menuTree = new ArrayList<>();
        
        // 找出顶级菜单并构建树
        for (Menu menu : menuList) {
            if (menu.getPid() == null || menu.getPid() == 0) {
                MenuTree tree = convertToTree(menu, menuList);
                menuTree.add(tree);
            }
        }
        
        return Result.success(menuTree);
    }

    // 将Menu转换为树形结构的辅助方法
    private MenuTree convertToTree(Menu menu, List<Menu> allMenus) {
        MenuTree tree = new MenuTree();
        tree.setId(menu.getId());
        tree.setName(menu.getName());
        tree.setPath(menu.getPath());
        tree.setComponent(menu.getComponent());
        tree.setIcon(menu.getIcon());
        tree.setPid(menu.getPid());
        tree.setSort(menu.getSort());
        tree.setTitle(menu.getTitle());

        // 设置meta信息
        MenuMeta meta = new MenuMeta();
        meta.setTitle(menu.getTitle());
        meta.setIcon(menu.getIcon());
        tree.setMeta(meta);
        
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

    /**
     * 获取角色的菜单ID列表
     * @param roleId
     * @return
     */
    @GetMapping("/role/menuIds/{roleId}")
    public Result<List<Long>> getRoleMenuIds(@PathVariable Long roleId) {
        List<Long> menuIds = roleMenuService.getRoleMenuIds(roleId);
        return Result.success(menuIds);
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