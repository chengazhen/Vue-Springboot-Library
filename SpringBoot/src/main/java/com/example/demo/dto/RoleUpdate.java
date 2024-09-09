package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleUpdate {

    @NotNull(message = "id 不能为空")
    private Long id;
    private String name;
    private String description;
}
