package com.jamestesh.simpleOrder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.jamestesh.simpleOrder.repositories.OrdersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;	
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	@Test
	public void test_create_new_brick_order() throws Exception{
	
		String body = this.restTemplate.getForObject("/orders/create/bricks/6000", String.class);
		assertThat(body).contains("orderReference");
		
	}

	@Test
	public void test_retrieve_single_order() throws Exception{
		
		String body = this.restTemplate.getForObject("/orders/get/1", String.class);
		assertThat(body).contains("Bricks");
		
	}
	
	@Test
	public void test_retrieve_single_order_invalidReference() throws Exception{
		
		String body = this.restTemplate.getForObject("/orders/get/60000", String.class);
		assertThat(body).isEqualTo(null);
		
	}
	
	@Test
	public void test_retrieve_all_orders() throws Exception{
		
		String body = this.restTemplate.getForObject("/orders/get/all", String.class);
		assertThat(body).contains("Bricks");
		
	}
	
	@Test
	public void test_update_order() throws Exception{
		
		String body = this.restTemplate.getForObject("/orders/update/1/55", String.class);
		assertThat(body).contains("55");
		
	}
	
}
