package com.example.mountaineerback.controller;

import com.example.mountaineerback.model.dto.LoginRequest;
import com.example.mountaineerback.model.dto.RegisterRequest;
import com.example.mountaineerback.model.dto.UserDTO;
import com.example.mountaineerback.response.ApiResponse;
import com.example.mountaineerback.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /login      登入
 * GET  /logout     登出
 * POST /register   註冊
 * */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        // login 判斷比對
        Optional<UserDTO> optUserDTO = userService.login(loginRequest);
        if(optUserDTO.isEmpty()) {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
        }
        // 存入 HttpSession 中
        session.setAttribute("userDTO", optUserDTO.get());
        return ResponseEntity.ok(ApiResponse.success("登入成功", optUserDTO.get()));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
//        session.removeAttribute("userDTO");
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.success("登出成功", "null"));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody RegisterRequest registerRequest, HttpSession session) {
        Optional<UserDTO> optUserDTO = userService.register(registerRequest);
        if(optUserDTO.get().getEmail().equals(registerRequest.getEmail())) {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "信箱重複！已經註冊過囉"));
        }
        return ResponseEntity.ok(ApiResponse.success("註冊成功", optUserDTO.get()));
    }

}
//    @GetMapping("/validate")
//    public ResponseEntity<ApiResponse<LoginDTO>> validate(HttpSession session) {
//
//    }
