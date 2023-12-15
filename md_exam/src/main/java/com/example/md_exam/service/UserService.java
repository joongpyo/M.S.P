package com.example.md_exam.service;

import com.example.md_exam.dto.UserDto;
import com.example.md_exam.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void setRegister(UserDto userDto){
        userMapper.setRegister(userDto);

    }

    public UserDto setLogin(UserDto userDto){
        return userMapper.setLogin(userDto);

    }


}
