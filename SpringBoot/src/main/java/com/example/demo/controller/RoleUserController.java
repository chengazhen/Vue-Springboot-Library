package com.example.demo.controller;

import com.example.demo.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/role-user")
public class RoleUserController {

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    /**
     * 为用户分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @return 操作结果
     */
    @PostMapping("/assign/{userId}")
    public ResponseEntity<String> assignRolesToUser(
            @PathVariable Long userId,
            @RequestBody Long[] roleIds) {
        sysRoleService.assignRolesToUser(userId, roleIds);
        return ResponseEntity.ok("角色分配成功");
    }

    /**
     * 移除用户的角色
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @return 操作结果
     */
    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<String> removeRolesFromUser(
            @PathVariable Long userId,
            @RequestBody Long[] roleIds) {
        sysRoleService.removeRolesFromUser(userId, roleIds);
        return ResponseEntity.ok("角色移除成功");
    }

    /**
     * 获取用户的所有角色
     * @param userId 用户ID
     * @return 角色列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserRoles(@PathVariable Long userId) {
        return ResponseEntity.ok(sysRoleService.getUserRoles(userId));
    }

    /**
     * 获取角色的所有用户
     * @param roleId 角色ID
     * @return 用户列表
     */
    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> getRoleUsers(@PathVariable Long roleId) {
        return ResponseEntity.ok(sysRoleService.getRoleUsers(roleId));
    }
}
