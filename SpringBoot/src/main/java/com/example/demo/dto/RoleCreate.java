package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleCreate {
    @NotNull(message = "name 不能为空")
    private String name;
    @NotNull(message = "description 不能为空")
    private String description;
}
