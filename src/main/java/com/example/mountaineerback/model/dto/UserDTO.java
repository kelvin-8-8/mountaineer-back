package com.example.mountaineerback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userid;
    private String password;
    private String username;
    private String role;
    private String status;
}
