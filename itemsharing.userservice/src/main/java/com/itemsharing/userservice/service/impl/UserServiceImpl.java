package com.itemsharing.userservice.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.repository.UserRepository;
import com.itemsharing.userservice.utils.SecurityUtility;
import com.itemsharing.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User createUser(User user) {
		User localUser = this.userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			LOGGER.info("User with username {} already exists. Nothing will be done.", user.getUsername());
			return null;
		}
		
		Set<UserRole> userRoles = new HashSet<>();
		Role localRole = new Role();
		localRole.setRoleId(1L);
		userRoles.add(new UserRole(user, localRole));
		
		user.getUserRoles().addAll(userRoles);
		user.setJoinDate(new Date());
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
		user.setPassword(encryptedPassword);
		
		localUser = this.userRepository.save(user);
		return localUser;
	}
	
	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

}
