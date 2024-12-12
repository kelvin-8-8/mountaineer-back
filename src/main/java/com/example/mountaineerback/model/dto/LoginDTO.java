package com.example.mountaineerback.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    private Boolean isLoggedIn; // 是否登入成功 ?
}
