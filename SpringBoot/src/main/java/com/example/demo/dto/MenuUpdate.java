package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuUpdate {
    @NotNull(message = "id 不能为空")
    private Long id;
    @NotBlank(message = "name 不能为空")
    private String name;
    @NotBlank(message = "path 不能为空")
    private String path;
    private String component;
    private String icon;
    @NotNull(message = "sort 不能为空")
    private Integer sort;
}
