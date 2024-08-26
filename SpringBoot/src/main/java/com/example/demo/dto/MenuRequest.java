package com.example.demo.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuRequest {
    @NotBlank(message = "name 不能为空")
    private String name;
    @NotBlank(message = "path 不能为空")
    private String path;
    private String component;
    private String icon;
    @NotNull(message = "pid 不能为空")
    private Integer pid;
    @NotNull(message = "sort 不能为空")
    private Integer sort;
}
