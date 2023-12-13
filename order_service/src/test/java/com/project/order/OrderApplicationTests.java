package com.project.order;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.order.dto.OrderLineItemsDto;
import com.project.order.dto.OrderRequest;
import com.project.order.entity.Order;
import com.project.order.repository.OrderRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderApplicationTests {

	static final MySQLContainer mySqlContainer;
	static{
		mySqlContainer = new MySQLContainer("mysql:5.7.34");
		mySqlContainer.start();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url",()-> mySqlContainer.getJdbcUrl());
		registry.add("spring.datasource.username",()->mySqlContainer.getUsername());
		registry.add("spring.datasource.password",()->mySqlContainer.getPassword());
		registry.add("spring.jpa.hibernate.ddl-auto",()->"create");
	}

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

//	@Test
//	void shouldCreateOrder() throws Exception {
//		String orderRequest = objectMapper.writeValueAsString(getOrderRequest());
//		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(orderRequest))
//								.andExpect(status().isCreated())
//								.andReturn();
//		String actualOrderRequest = res.getResponse().getContentAsString();
//		String order = orderRepository.findAll().toString();
////		Assertions.assertEquals(actualOrderRequest, order);
//		Assertions.assertEquals(orderRepository.findAll().size(), 1);
//	}

	private OrderRequest getOrderRequest() {
		return OrderRequest.builder()
				.orderLineItemsDtoList(getOrderLineItemsDtoList())
				.build();
	}

	private List<OrderLineItemsDto> getOrderLineItemsDtoList() {
		List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();
		orderLineItemsDtoList.add(new OrderLineItemsDto(null, "Iphone 12 pro", new BigDecimal("200000"), 1));
		orderLineItemsDtoList.add(new OrderLineItemsDto(null, "Naksh Phone", new BigDecimal("1200000"), 1));
		return orderLineItemsDtoList;
	}


}
