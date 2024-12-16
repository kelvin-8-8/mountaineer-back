package com.example.mountaineerback.controller;

import com.example.mountaineerback.model.dto.LoginRequest;
import com.example.mountaineerback.model.dto.RegisterRequest;
import com.example.mountaineerback.model.dto.UserDTO;
import com.example.mountaineerback.response.ApiResponse;
import com.example.mountaineerback.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        // login 判斷比對
        log.info("使用者:{}. 開始登入", loginRequest.getUsername());
        Optional<UserDTO> optUserDTO = userService.login(loginRequest);
        if(optUserDTO.isEmpty()) {
            return ResponseEntity.status(403).body(ApiResponse.error(403, "登入失敗"));
        }
        // 存入 HttpSession 中
        session.setAttribute("userDTO", optUserDTO.get());
        log.info("使用者:{} email:{}. 登入成功", optUserDTO.get().getUsername(), optUserDTO.get().getEmail());
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

        log.info("使用者:{} email:{}. 開始註冊", registerRequest.getUsername(), registerRequest.getEmail());
        Optional<UserDTO> optUserDTO = userService.register(registerRequest);
        if(optUserDTO.isEmpty()) {
            return ResponseEntity.status(403).body(ApiResponse.error(403, "未知錯誤"));
        }

        if(optUserDTO.get().getEmail().equals(registerRequest.getEmail()) && optUserDTO.get().getUsername().equals(registerRequest.getUsername())) {
            return ResponseEntity.ok(ApiResponse.success("註冊成功", optUserDTO.get()));
        }

        if(optUserDTO.get().getEmail().equals(registerRequest.getEmail())) {
            return ResponseEntity.status(403).body(ApiResponse.error(403, "信箱已被註冊"));
        }

        if(optUserDTO.get().getUsername().equals(registerRequest.getUsername())) {
            return ResponseEntity.status(403).body(ApiResponse.error(403, "使用者名稱已被註冊"));
        }
        log.info("使用者:{} email:{}. 註冊成功.", registerRequest.getUsername(), registerRequest.getEmail());
        return ResponseEntity.ok(ApiResponse.success("註冊成功", optUserDTO.get()));
    }

}
//    @GetMapping("/validate")
//    public ResponseEntity<ApiResponse<LoginDTO>> validate(HttpSession session) {
//
//    }
