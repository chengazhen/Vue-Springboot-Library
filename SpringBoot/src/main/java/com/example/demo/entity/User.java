package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@TableName("user")
@Data
public class User {
    @TableId (type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "用户名不为空")
    private String username;
    private String nickName;
    @NotEmpty(message = "密码不为空")
    private String password;
    private String sex;
    private String address;
    private String phone;
    @TableField(exist = false)  //表中没有token不会报错仍能编译运行
    private String token;
    private Integer role;

}
