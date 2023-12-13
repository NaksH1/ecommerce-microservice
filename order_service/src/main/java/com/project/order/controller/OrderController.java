package com.project.order.controller;

import com.project.order.dto.OrderRequest;
import com.project.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest){
        if(orderRequest != null) {
            orderService.placeOrder(orderRequest);
            return new ResponseEntity<>(orderRequest, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
