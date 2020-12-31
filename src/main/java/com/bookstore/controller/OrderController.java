package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dao.entity.Order;
import com.bookstore.dao.impl.OrderDaoImpl;
import com.bookstore.dao.impl.OrderForm;

@RestController
public class OrderController {
	@Autowired
	OrderDaoImpl order;
	
	@PostMapping(value = "/orders")
	public Order saveOrder(@RequestBody OrderForm orderForm) {
		return order.saveOrder(orderForm);
	}
	
	@GetMapping(value = "/allOrders")
	public List<Order> getAll(){
		return order.getAllOrders();
	}

}
