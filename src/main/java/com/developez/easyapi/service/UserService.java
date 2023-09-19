package com.developez.easyapi.service;

import com.developez.easyapi.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
