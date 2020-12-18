package com.itemsharing.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	
	@PostMapping("/save")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
}
