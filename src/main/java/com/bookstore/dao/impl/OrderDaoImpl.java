package com.bookstore.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.dao.IOrderDao;
import com.bookstore.dao.entity.Book;
import com.bookstore.dao.entity.Order;
import com.bookstore.dao.entity.OrderItem;
import com.bookstore.dao.entity.User;
import com.bookstore.dao.repository.BookRepository;
import com.bookstore.dao.repository.OrderItemRepository;
import com.bookstore.dao.repository.OrderRepository;
import com.bookstore.dao.repository.UserRepository;
@Repository
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(OrderForm orderForm) {
		User user = new User();
		user.setId(orderForm.getUser().getId());
		user.setUserName(orderForm.getUser().getUserName());
		user.setActive(orderForm.getUser().isActive());
		user.setRoles(orderForm.getUser().getRoles());
		user.setPassword(orderForm.getUser().getPassword());
		Order order = new Order();
		order.setUser(user);
		order.setDate(new Date());
		order = orderRepository.save(order);
		double total = 0;
		for (OrderBook p : orderForm.getBooks()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			Book product = bookRepository.findById(p.getId()).get();
			System.out.println(product);
			orderItem.setBook(product);
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(p.getQuantity());
			orderItemRepository.save(orderItem);
			total += p.getQuantity() * product.getPrice();
		}
		order.setTotalAmount(total);
		return orderRepository.save(order);
	}
	
	public double getTotal(OrderForm orderForm) {
		double total = 0;
		for (OrderBook p : orderForm.getBooks()) {
			Book product = bookRepository.findById(p.getId()).get();
			total += p.getQuantity() * product.getPrice();
		}
		return total;
	}
	
	public Order getOrder(Long id) {
		return orderRepository.getOne(id);
	}

}
