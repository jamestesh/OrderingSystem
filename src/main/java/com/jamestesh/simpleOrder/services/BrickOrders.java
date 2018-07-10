package com.jamestesh.simpleOrder.services;

import java.util.Iterator;
import java.util.List;

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

	@Override
	public Orders retrieveOrder(long orderReference) {
		
		try {	
			Orders order = ordersRepo.findById(orderReference).get();
			
			if(order != null) {
				return order;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public Iterable<Orders> getAllOrders() {
	
		Iterable<Orders> allOrders = ordersRepo.findAll();
		
		return allOrders;
	}

	@Override
	public Orders updateOrderQuantity(long orderReference, int quantity) {
				
		Orders order = ordersRepo.findById(orderReference).get();
		order.setQuantity(quantity);
		ordersRepo.save(order);
		
		// TODO Auto-generated method stub
		return order;
	}

}
