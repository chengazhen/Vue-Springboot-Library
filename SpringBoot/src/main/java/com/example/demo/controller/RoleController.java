package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.dto.RoleCreate;
import com.example.demo.entity.Role;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
    @GetMapping("/list")
    public Result list() {
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        return Result.success();
    }

    @PutMapping("/update")
    public  Result<?> update(@RequestBody Role role){
        return Result.success();
    }

    @PostMapping("/create")
    public  Result<?> create(@RequestBody @Valid RoleCreate role){
        return Result.success();
    }

}
