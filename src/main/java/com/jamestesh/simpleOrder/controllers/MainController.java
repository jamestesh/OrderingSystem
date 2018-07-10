package com.jamestesh.simpleOrder.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamestesh.simpleOrder.models.Orders;
import com.jamestesh.simpleOrder.services.BrickOrders;

@RestController
public class MainController {

	@Autowired
	private BrickOrders brickOrders;
	
	@GetMapping("/orders/create/bricks/{quantity}")
	public Map<String, Long> index(@PathVariable int quantity) {
		
		Orders newOrder = brickOrders.createOrder(quantity);
		
		Map<String, Long> returnMap = new HashMap<String, Long>();
		returnMap.put("orderReference", newOrder.getId());
		
		return returnMap;
	}
	
}
