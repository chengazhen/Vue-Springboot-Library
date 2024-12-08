package com.example.demo.dto;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import lombok.Data;
import java.util.List;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String nickName;
    private String phone;
    private String address;
    private List<Role> roles;

    public UserInfo(User user, List<Role> roles) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickName = user.getNickName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.roles = roles;
    }
} 