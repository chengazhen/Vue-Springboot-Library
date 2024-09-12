package com.example.demo.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRoleUpdate {
    @NotNull(message = "id 不能为空")
    private Long id;

    @NotNull(message = "userId 不能为空")
    private Long userId;

    @NotNull(message = "roleIds 不能为空")
    @Size(min = 1, message = "roleIds 不能为空")
    private Long[] roleIds;
}
