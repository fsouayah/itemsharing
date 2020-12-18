package com.itemsharing.itemservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

	Item findByName(String name);
	
	List<Item> findByUser(User user);
	
	List<Item> findAll();

}
