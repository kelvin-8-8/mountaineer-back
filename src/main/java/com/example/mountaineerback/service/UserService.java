package com.example.mountaineerback.service;

import com.example.mountaineerback.model.dto.LoginDTO;
import com.example.mountaineerback.model.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByUsername(String username);
    Optional<UserDTO> login(LoginDTO loginDTO);
    Optional<UserDTO> saveUser(UserDTO userDTO);

    // 用戶關注列表(用戶關注那些商品)


    // 商品關注列表(商品被那些用戶關注)


    // 新增商品關注
    public void addFavoriteProduct(Long userId, Long productId);

    // 移除商品關注
    public void removeFavoriteProduct(Long userId, Long productId);
}
