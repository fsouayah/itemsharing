package com.itemsharing.itemservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class ItemServiceApplication implements CommandLineRunner {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		User user1 = this.userService.getUserByUsername("gon");

		Item item1 = new Item();
		item1.setName("Item1");
		item1.setItemCondition("New");
		item1.setStatus("Active");
		item1.setAddDate(new Date());
		item1.setDescription("This is item1 description.");
		item1.setUser(user1);

		this.itemService.addItemByUser(item1, user1.getUsername());


		User user2 = this.userService.getUserByUsername("killua");

		Item item2 = new Item();
		item2.setName("item2");
		item2.setItemCondition("Used");
		item2.setStatus("Inactive");
		item2.setAddDate(new Date());
		item2.setDescription("This is item2 description.");
		item2.setUser(user2);

		this.itemService.addItemByUser(item2, user2.getUsername());
		 */
	}

}
