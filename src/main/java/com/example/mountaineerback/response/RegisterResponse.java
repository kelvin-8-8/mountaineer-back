package com.example.mountaineerback.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String username;
    private String trueName;
    private String email;
    private String role;

}
