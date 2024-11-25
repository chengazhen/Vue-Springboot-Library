package com.example.demo.controller;

import com.example.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role-menu")
public class RoleMenuController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 为角色分配菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return 操作结果
     */
    @PostMapping("/assign/{roleId}")
    public ResponseEntity<String> assignMenusToRole(
            @PathVariable Long roleId,
            @RequestBody Long[] menuIds) {
        sysRoleService.assignMenusToRole(roleId, menuIds);
        return ResponseEntity.ok("菜单权限分配成功");
    }

    /**
     * 移除角色的菜单权限
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     * @return 操作结果
     */
    @DeleteMapping("/remove/{roleId}")
    public ResponseEntity<String> removeMenusFromRole(
            @PathVariable Long roleId,
            @RequestBody Long[] menuIds) {
        sysRoleService.removeMenusFromRole(roleId, menuIds);
        return ResponseEntity.ok("菜单权限移除成功");
    }

    /**
     * 获取角色的所有菜单权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> getRoleMenus(@PathVariable Long roleId) {
        return ResponseEntity.ok(sysRoleService.getRoleMenus(roleId));
    }

    /**
     * 获取菜单的所有授权角色
     * @param menuId 菜单ID
     * @return 角色列表
     */
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<?> getMenuRoles(@PathVariable Long menuId) {
        return ResponseEntity.ok(sysRoleService.getMenuRoles(menuId));
    }
} 