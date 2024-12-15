package com.example.mountaineerback.service.impl;

import com.example.mountaineerback.model.dto.LoginRequest;
import com.example.mountaineerback.model.dto.RegisterRequest;
import com.example.mountaineerback.model.dto.UserDTO;
import com.example.mountaineerback.model.entity.User;
import com.example.mountaineerback.repository.UserRepository;
import com.example.mountaineerback.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<UserDTO> login(LoginRequest loginRequest) {
        Optional<User> optUser = userRepository.findByUsername(loginRequest.getUsername());
        // 判斷密碼
        if(optUser.isPresent() && optUser.get().getPassword().equals(loginRequest.getPassword())) {
            return Optional.of(modelMapper.map(optUser.get(), UserDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> register(RegisterRequest registerRequest) {
        Optional<User> optUser = userRepository.findByEmail(registerRequest.getEmail());
        if(optUser.isEmpty()) {
            User user = modelMapper.map(registerRequest, User.class);
            user.setRole("ROLE_GUEST"); //預設值
            userRepository.save(user);
            optUser = userRepository.findByEmail(registerRequest.getEmail());
        }
        return Optional.of(modelMapper.map(optUser.get(), UserDTO.class));
    }

}
