package com.itemsharing.itemservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.itemsharing.itemservice.client.UserFeignClient;
import com.itemsharing.itemservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private final UserService userService;
	
	private final ItemRepository itemRepository;

	private final UserFeignClient userFeignClient;

	@Autowired
	public ItemServiceImpl(UserService userService, ItemRepository itemRepository, UserFeignClient userFeignClient) {
		this.userService = userService;
		this.itemRepository = itemRepository;
		this.userFeignClient = userFeignClient;
	}

	@Override
	public Item addItemByUser(Item item, String username) {
		Item localItem = this.itemRepository.findByName(item.getName());
		
		if (localItem != null) {
			LOGGER.info("Item with name {} already exists. Nothing will be done.", item.getName());
			return null;
		} else {
			Date today = new Date();
			item.setAddDate(today);
			
			User user = this.userService.getUserByUsername(username);
			item.setUser(user);
			Item newItem = this.itemRepository.save(item);
			
			return newItem;
		}
	}

	@Override
	public List<Item> getAllItems() {
		return this.itemRepository.findAll();
	}

	@Override
	public List<Item> getItemsByUsername(String username) {
		User user = this.userService.getUserByUsername(username);
		return this.itemRepository.findByUser(user);
	}

	@Override
	public Item getItemById(Long id) {
		return this.itemRepository.findById(id).orElse(null);
	}

	@Override
	public Item updateItem(Item item) throws IOException {
		// return this.itemRepository.save(item);
		
		Item localItem = getItemById(item.getId());
		
		if (localItem == null) {
			throw new IOException("No item found.");
		} else {
			localItem.setName(item.getName());
			localItem.setItemCondition(item.getItemCondition());
			localItem.setStatus(item.getStatus());
			localItem.setDescription(item.getDescription());
			
			return this.itemRepository.save(localItem);
		}
	}

	@Override
	public void deleteItemById(Long id) {
		this.itemRepository.deleteById(id);
	}

	@Override
//	@HystrixCommand(commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")})
	@HystrixCommand(
		fallbackMethod = "buildFallbackUser",
		threadPoolKey = "itemByUserThreadPool",
		threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "30"),
			// Netflix recommendation =
			// (requests per second at peak when the service is healthy * 99th percentile latency in seconds)
			// + small amount of extra threads for overhead
			@HystrixProperty(name = "maxQueueSize", value = "10")
		}
	)
	public User getUserByUsername(String username) {
//		return this.userService.getUserByUsername(username);
//		randomlyRunLong();

		LOGGER.debug("ItemService CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());
		LOGGER.debug("ItemService AuthToken: {}", UserContextHolder.getContext().getAuthToken());
		LOGGER.debug("ItemService UserId: {}", UserContextHolder.getContext().getUserId());

		return this.userFeignClient.getUserByUsername(username);
	}

	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum = rand.nextInt(3) + 1;
		if (randomNum == 3) sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	private User buildFallbackUser(String username) {
		User user = new User();
		user.setId(123123L);
		user.setUsername("Temp Username");

		return user;
	}
}
