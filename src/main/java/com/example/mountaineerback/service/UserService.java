package com.example.mountaineerback.service;

import com.example.mountaineerback.model.request.LoginRequest;
import com.example.mountaineerback.model.request.RegisterRequest;
import com.example.mountaineerback.model.dto.UserDTO;


import java.util.Optional;

public interface UserService {
//    Optional<UserDTO> findByUsername(String username);
    Optional<UserDTO> login(LoginRequest loginRequest);
    Optional<UserDTO> register(RegisterRequest registerRequest);

    // 用戶

}
