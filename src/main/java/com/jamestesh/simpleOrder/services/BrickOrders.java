package com.jamestesh.simpleOrder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamestesh.simpleOrder.models.Orders;
import com.jamestesh.simpleOrder.repositories.OrdersRepository;
import com.jamestesh.simpleOrder.services.interfaces.OrdersInterface;

@Service
public class BrickOrders implements OrdersInterface{

	private String orderType = "Bricks";
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	public Orders createOrder(int quantity) {
		
		Orders newOrder = new Orders();
		newOrder.setType(orderType);
		newOrder.setQuantity(quantity);
		
		// TODO Auto-generated method stub
		return ordersRepo.save(newOrder);
		
	}

}
