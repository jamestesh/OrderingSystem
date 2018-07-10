package com.jamestesh.simpleOrder.services.interfaces;

import java.util.List;

import com.jamestesh.simpleOrder.models.Orders;

public interface OrdersInterface {

	public Orders createOrder(int quantity);
	
	public Orders retrieveOrder(long orderReference);
	
	public Iterable<Orders> getAllOrders();
	
	public Orders updateOrderQuantity(long orderReference, int quantity);
	
}
