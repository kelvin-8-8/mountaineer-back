package com.example.mountaineerback.service.impl;

import com.example.mountaineerback.model.request.LoginRequest;
import com.example.mountaineerback.model.request.RegisterRequest;
import com.example.mountaineerback.model.dto.UserDTO;
import com.example.mountaineerback.model.entity.User;
import com.example.mountaineerback.repository.UserRepository;
import com.example.mountaineerback.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.mountaineerback.model.enums.USER_ROLE.ROLE_GUEST;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 登入
    @Override
    public Optional<UserDTO> login(LoginRequest loginRequest) {
        Optional<User> optUser = userRepository.findByUsername(loginRequest.getUsername());
        // 判斷密碼
        if(optUser.isPresent() && optUser.get().getPassword().equals(loginRequest.getPassword())) {
            return Optional.of(modelMapper.map(optUser.get(), UserDTO.class));
        }
        return Optional.empty();
    }

    // 註冊
    @Override
    public Optional<UserDTO> register(RegisterRequest registerRequest) {

        // isEmpty:代表沒有重複   isPresent:代表有重複的用戶
        Optional<User> optUserEmail = userRepository.findByEmail(registerRequest.getEmail());
        Optional<User> optUserUserName = userRepository.findByUsername(registerRequest.getUsername());

        if(optUserEmail.isPresent()) {
            return Optional.of(modelMapper.map(optUserEmail.get(), UserDTO.class));
        }

        if (optUserUserName.isPresent()) {
            return Optional.of(modelMapper.map(optUserUserName.get(), UserDTO.class));
        }

        if(optUserUserName.isEmpty() && optUserEmail.isEmpty()) {
            User user = modelMapper.map(registerRequest, User.class);
            user.setRole(ROLE_GUEST); //預設值
            userRepository.save(user);
            return Optional.of(modelMapper.map(user, UserDTO.class));
        }

        return Optional.empty();
    }

}
