package com.newproject.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.newproject.activity.entity.Users;
import com.newproject.activity.payload.UserDto;
import com.newproject.activity.repository.UserRepository;
import com.newproject.activity.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userservice;
//store the user to db
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    	return new ResponseEntity<>(userservice.createUser(userDto),HttpStatus.CREATED);
       
    }
}
