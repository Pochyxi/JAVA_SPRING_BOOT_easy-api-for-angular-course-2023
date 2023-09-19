package com.developez.easyapi.controller;

import com.developez.easyapi.payload.UserDto;
import com.developez.easyapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>( users, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto user = userService.getUserById( id );
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser( userDto );
        return new ResponseEntity<>( user, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("id") Long id) {
        UserDto user = userService.updateUser( userDto, id );
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser( id );
        return new ResponseEntity<>( HttpStatus.OK );
    }
}
