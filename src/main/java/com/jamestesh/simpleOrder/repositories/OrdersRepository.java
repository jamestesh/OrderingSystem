package com.jamestesh.simpleOrder.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jamestesh.simpleOrder.models.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Long>{

}
