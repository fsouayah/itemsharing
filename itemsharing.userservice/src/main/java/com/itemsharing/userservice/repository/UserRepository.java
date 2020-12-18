package com.itemsharing.userservice.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import com.itemsharing.userservice.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String username);

}
