package com.itemsharing.userservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.service.UserService;
import com.itemsharing.userservice.utils.SecurityUtility;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*
		User user1 = new User();
		user1.setFirstName("Gon");
		user1.setLastName("Freecss");
		user1.setUsername("gon");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("password"));
		user1.setPassword("password");
		user1.setEmail("gon.freecss@gmail.com");

		User user2 = new User();
		user2.setFirstName("Killua");
		user2.setLastName("Zoldyck");
		user2.setUsername("killua");
//		user2.setPassword(SecurityUtility.passwordEncoder().encode("password"));
		user2.setPassword("password");
		user2.setEmail("killua.zoldyck@gmail.com");

		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1L);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		userRoles.add(new UserRole(user2, role1));

		this.userService.createUser(user1);
		this.userService.createUser(user2);
		*/
	}
}
