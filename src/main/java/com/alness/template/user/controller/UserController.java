package com.alness.template.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alness.template.user.dto.RequestUserDto;
import com.alness.template.user.dto.ResponseUserDto;
import com.alness.template.user.service.UsersService;

@RestController
@RequestMapping("${apiPrefix}/users")
public class UserController {
    @Autowired
    private UsersService userService;


    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getAllUsers(){
        List<ResponseUserDto> users = userService.findAll();
        return new ResponseEntity<List<ResponseUserDto>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@RequestParam String id){
        ResponseUserDto user = userService.findOne(id);
        return new ResponseEntity<ResponseUserDto>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto user){
        ResponseUserDto newUser = userService.save(user);
        return new ResponseEntity<ResponseUserDto>(newUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(){
        String user = "actualizas un usuario";
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        String user = "borras un usuario";
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
