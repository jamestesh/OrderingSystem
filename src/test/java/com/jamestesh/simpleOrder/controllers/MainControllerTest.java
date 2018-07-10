package com.jamestesh.simpleOrder.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.jamestesh.simpleOrder.models.Orders;
import com.jamestesh.simpleOrder.repositories.OrdersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private OrdersRepository orderRepo;
	
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
		
		Iterable<Orders> listOfAllOrders = orderRepo.findAll();
		
		for(Orders order : listOfAllOrders) {
			
			if(order.getState().equals("Dispatched")) {
				
				HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.TEXT_HTML);
				HttpEntity<?> entity = new HttpEntity<>(headers);
				
				ResponseEntity<String> response = restTemplate.exchange("/orders/update/" + order.getId() + "/55" ,HttpMethod.GET, entity, String.class);
				
				assertThat(response.getStatusCode().toString()).isEqualTo("400");
				
			}
			else {
				String body = this.restTemplate.getForObject("/orders/update/" + order.getId() + "/55", String.class);
				assertThat(body).contains("55");
			}
			
		}
		
		
		
	}
	
	@Test
	public void test_update_order_state() throws Exception{
		
		String body = this.restTemplate.getForObject("/orders/update/markDispatched/1", String.class);
		assertThat(body).contains("Dispatched");
		

	}
	
	@Test
	public void test_update_order_stateBadId() throws Exception{
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange("/orders/update/markDispatched/111111" ,HttpMethod.GET, entity, String.class);
		
		assertThat(response.getStatusCode().toString()).isEqualTo("400");
		
	}

	
}
