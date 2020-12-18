package com.itemsharing.itemservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.UserRepository;
import com.itemsharing.itemservice.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

}
