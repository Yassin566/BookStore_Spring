package com.bookstore.dao;

import java.util.List;

import com.bookstore.dao.entity.Order;
import com.bookstore.dao.impl.OrderForm;

public interface IOrderDao {
	List<Order> getAllOrders();

	Order saveOrder(OrderForm orderForm);
     double getTotal(OrderForm orderForm);
}
