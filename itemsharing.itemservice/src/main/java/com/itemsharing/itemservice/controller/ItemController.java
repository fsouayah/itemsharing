package com.itemsharing.itemservice.controller;

import java.io.IOException;
import java.util.List;

import com.itemsharing.itemservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.service.ItemService;

@RestController
@RequestMapping("/v1/item")
public class ItemController {

	private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping("/add/{username}")
	public Item addItem(@PathVariable String username, @RequestBody Item item) {
		return this.itemService.addItemByUser(item, username);
	}
	
	@GetMapping("/itemsByUser/{username}")
	public List<Item> getAllItemsByUser(@PathVariable String username) {
		return this.itemService.getItemsByUsername(username);
	}
	
	@GetMapping("/all")
	public List<Item> getAllItems() {
		return this.itemService.getAllItems();
	}
	
	@GetMapping("/{id}")
	public Item getItemById(@PathVariable Long id) {
		return this.itemService.getItemById(id);
	}
	
	@PutMapping("/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item item) throws IOException {
		item.setId(id);
		return this.itemService.updateItem(item);
	}
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable Long id) {
		this.itemService.deleteItemById(id);
	}
	
	@GetMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username) {
		logger.debug("ItemController CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());
		logger.debug("ItemController AuthToken: {}", UserContextHolder.getContext().getAuthToken());
		logger.debug("ItemController UserId: {}", UserContextHolder.getContext().getUserId());
		return this.itemService.getUserByUsername(username);
	}
}
