package com.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.dao.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
